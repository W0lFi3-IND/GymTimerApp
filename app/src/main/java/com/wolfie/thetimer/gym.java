package com.wolfie.thetimer;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.sdsmdg.harjot.crollerTest.Croller;
import com.sdsmdg.harjot.crollerTest.OnCrollerChangeListener;

public class gym extends AppCompatActivity {
Croller mCroller;
TextView tv;
MediaPlayer mp;
Button btn;
Boolean counter=false;
Vibrator v;
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
    mp.stop();

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
                 mp= MediaPlayer.create(getApplicationContext(),R.raw.timer);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    v.vibrate(VibrationEffect.createOneShot(2000, VibrationEffect.DEFAULT_AMPLITUDE));
                } else {
                    //deprecated in API 26
                    v.vibrate(2000);
                }
                mp.start();

                start();


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
        v =(Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        Toast.makeText(this,"Buzzer will ring after everytime the countdown is over and you have to manually stop the timer after you have done using it.",Toast.LENGTH_LONG).show();
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
