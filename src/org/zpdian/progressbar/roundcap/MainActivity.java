package org.zpdian.progressbar.roundcap;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {

    boolean running;
    CustomArc mProgress;
    int progress = 0;
    Thread mThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mProgress = (CustomArc) findViewById(R.id.progress);
        final Runnable r = new Runnable() {
            public void run() {
                running = true;
                while (progress < 101) {
                    mProgress.setProgress(progress);
                    progress = progress + 1;
                    try {
                        Thread.sleep(10 + progress);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
                running = false;
            }
        };

        mProgress.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if (!running) {
                    progress = 0;
                    mProgress.resetCount();
                    mThread = new Thread(r);
                    mThread.start();
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

}
