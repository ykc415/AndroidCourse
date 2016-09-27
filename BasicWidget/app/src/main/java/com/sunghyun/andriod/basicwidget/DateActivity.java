package com.sunghyun.andriod.basicwidget;

import android.os.SystemClock;
import android.support.v4.media.RatingCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;

public class DateActivity extends AppCompatActivity {

    Chronometer timer;
    Button start;
    Button stop;
    Button pause;

    // 현재 일시정지 버튼이 눌렸는지 체크
    boolean puaseFlag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date);

        timer = (Chronometer) findViewById(R.id.chrono);

        start = (Button) findViewById(R.id.startbutton);
        stop = (Button) findViewById(R.id.stopbutton);
        pause = (Button) findViewById(R.id.pausebutton);

        start.setOnClickListener(mOnClickListener);
        stop.setOnClickListener(mOnClickListener);
        pause.setOnClickListener(mOnClickListener);
    }

    View.OnClickListener mOnClickListener = new View.OnClickListener() {
        boolean flag = false;
        long time=0;
        long stime=0;
        @Override
        public void onClick(View view) {

            switch(view.getId()) {
                case R.id.startbutton:
                    if(flag) {
                        timer.setBase(SystemClock.elapsedRealtime()-time);
                        timer.start();
                    }else{
                    stime = SystemClock.elapsedRealtime();
                    timer.setBase(stime);
                    timer.start();
                    }

                    break;
                case R.id.stopbutton:
                    timer.stop();
                    flag = false;

                    break;
                case R.id.pausebutton:
                    timer.stop();
                    time = SystemClock.elapsedRealtime()-stime;
                    flag = true;
                    break;
            }
        }
    };
}
