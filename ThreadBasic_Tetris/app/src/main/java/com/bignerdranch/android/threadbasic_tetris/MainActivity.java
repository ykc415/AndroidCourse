package com.bignerdranch.android.threadbasic_tetris;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity {

    FrameLayout ground;
    Button buttonUp, buttonDown, buttonLeft, buttonRight;

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch(msg.what) {
                case Stage.REFRESH:
                    // 화면갱신을 stage에 요청한다
                    break;
            }
        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ground = (FrameLayout) findViewById(R.id.stageLayout);

        buttonDown = (Button) findViewById(R.id.button_down);
        buttonUp = (Button) findViewById(R.id.button_up);
        buttonLeft = (Button) findViewById(R.id.button_left);
        buttonRight = (Button) findViewById(R.id.button_right);

    }


}
