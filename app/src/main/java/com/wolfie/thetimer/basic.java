package com.wolfie.thetimer;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.sdsmdg.harjot.crollerTest.Croller;
import com.sdsmdg.harjot.crollerTest.OnCrollerChangeListener;

public class basic extends AppCompatActivity {
    Croller mCroller;
    TextView tv;
    Button btn;
    Boolean counter=false;
    CountDownTimer mCountDownTimer;
    EditText mEdit;
    int a;
    public void reset()
    {
        tv.setText("0:00");
        mCroller.setProgress(0);
        mCountDownTimer.cancel();
        btn.setText("START");
        mCroller.setEnabled(true);
        counter=false;
    }

    public void updatetimer(int sec)
    {
        int minutes = (int)sec/60;
        int seconds = (int) (sec-minutes*60);
        String secondstring=Integer.toString(seconds);
        if(seconds<=9)
        {
            secondstring="0"+secondstring;

        }             tv.setText(Integer.toString(minutes)+":"+secondstring);
    }
    public void Controller(View view)
    {
        if(counter==false) {
            counter = true;

            mCroller.setEnabled(false);
            btn.setText("STOP");

            mCountDownTimer = new CountDownTimer((int) a * 1000 + 100, 1000) {

                @Override

                public void onTick(long millisUntilFinished) {

                    updatetimer((int) millisUntilFinished / 1000);
                    mCroller.setProgress((int) millisUntilFinished / 1000);
                }


                @Override
                public void onFinish() {
                    MediaPlayer mp= MediaPlayer.create(getApplicationContext(),R.raw.timerbasic);
                    mp.start();
                    reset();


                }
            }.start();
        } else{
            reset();

        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gym);
        tv= findViewById(R.id.textView);
        mCroller=(Croller) findViewById(R.id.croller);
        btn=findViewById(R.id.btn1);
        mCroller.setOnCrollerChangeListener(new OnCrollerChangeListener() {
            @Override
            public void onProgressChanged(Croller croller, int progress) {
                updatetimer((int) progress);
                a=(int) progress;
            }

            @Override
            public void onStartTrackingTouch(Croller croller) {

            }

            @Override
            public void onStopTrackingTouch(Croller croller) {

            }
        });

    }
}
