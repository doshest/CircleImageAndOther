
package com.dosh.circleimage;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends Activity implements OnProgressBarListener {
    LoadingButton btn;

    private Timer timer;

    private NumberProgressBar bnp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = (LoadingButton)findViewById(R.id.loading_btn);
        btn.setTargetProgress(360);
        btn.setStartListener(new LoadingButton.OnstartDownloadListener() {

            @Override
            public void startDownload() {
                new Asyn().execute("sssssssssssss");
            }
        });
        btn.setCallback(new LoadingButton.Callback() {

            @Override
            public void complete() {
                // TODO Auto-generated method stub
                Toast.makeText(getApplicationContext(), "下载完成,可以在这里写完成的回调方法", Toast.LENGTH_SHORT).show();
            }
        });

        bnp = (NumberProgressBar)findViewById(R.id.numberbar6);
        bnp.setOnProgressBarListener(this);
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        bnp.incrementProgressBy(1);
                    }
                });
            }
        }, 1000, 100);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        final int id = item.getItemId();
        if (id == R.id.action_settings) {
            btn.setTargetProgress(1000);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    class Asyn extends AsyncTask<String, Integer, String> {

        @Override
        protected String doInBackground(String... params) {
            // TODO Auto-generated method stub
            for (int i = 0; i < 360; i++) {
                btn.setCurrentProgress(i);
                try {
                    Thread.currentThread().sleep(50);
                } catch (final InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            // TODO Auto-generated method stub

            super.onPostExecute(result);
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        timer.cancel();
    }

    @Override
    public void onProgressChange(int current, int max) {
        if (current == max) {
            Toast.makeText(getApplicationContext(), "finish", Toast.LENGTH_SHORT).show();
            bnp.setProgress(0);
        }
    }
}
