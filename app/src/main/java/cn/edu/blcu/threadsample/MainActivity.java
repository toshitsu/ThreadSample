package cn.edu.blcu.threadsample;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView countDownTextView = null;
    private Button startButton;
    private Handler doCountdownOutputHandler;
    private int toCountDown = 10;
    private int fromCountDown = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        countDownTextView = (TextView)findViewById(R.id.countDownTextView);

        startButton = (Button) findViewById(R.id.startButton);
        startButton.setOnClickListener(this);

        doCountdownOutputHandler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                int c = toCountDown;
                while (c >= 0) {
                    countDownTextView.setText(String.valueOf(toCountDown));
                    Log.i("joerg", String.valueOf(c));
                    c = c - 1;
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
//            e.printStackTrace();
                    }
                }
            }
        };
    }

    void CountDown(final int from, final int to) {

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

                    //we can not access the UI Thread directly from another thread
//                    Message msg = new Message();
//                    msg.what = c;
                    toCountDown = from;
                    doCountdownOutputHandler.sendEmptyMessage(0);
//                finished
//                ...
            }
        });
        thread.start();
    }

    @Override
    public void onClick(View v) {
        CountDown(10, 0);
    }
}
