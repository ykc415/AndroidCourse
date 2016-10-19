package com.kodonho.android.threadbasic_tetris;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    FrameLayout ground;
    Button btnUp,btnDown,btnLeft,btnRight;

    int deviceWidth = 0;
    int deviceHeight = 0;

    int block_pixel_unit = 0;
    private static final int WIDTH_MAX_COUNT = 23;

    Stage stage;

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case Stage.REFRESH:
                    // 화면갱신을 Stage에 요청한다
                    stage.invalidate();
                    break;
                case Stage.NEW_BLOCK:
                    stage.setBlock();
                    stage.invalidate();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ground = (FrameLayout) findViewById(R.id.singleStage);

        btnUp = (Button) findViewById(R.id.btnUp);
        btnUp.setOnClickListener(this);
        btnDown = (Button) findViewById(R.id.btnDown);
        btnDown.setOnClickListener(this);
        btnLeft = (Button) findViewById(R.id.btnLeft);
        btnLeft.setOnClickListener(this);
        btnRight = (Button) findViewById(R.id.btnRight);
        btnRight.setOnClickListener(this);

        DisplayMetrics metrics = getResources().getDisplayMetrics();
        deviceWidth = metrics.widthPixels;
        deviceHeight = metrics.heightPixels;

        block_pixel_unit = deviceWidth / WIDTH_MAX_COUNT;

        stage = new Stage(this, handler, block_pixel_unit);
        ground.addView(stage);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btnUp:
                stage.rotateBlock();
                break;
            case R.id.btnDown:
                stage.downBlock();
                break;
            case R.id.btnLeft:
                stage.leftBlock();
                break;
            case R.id.btnRight:
                stage.rightBlock();
                break;
        }
    }
}
