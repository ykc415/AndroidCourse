package com.bignerdranch.android.threadbasic_tetris;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity {

    FrameLayout ground;
    Button buttonUp, buttonDown, buttonLeft, buttonRight;

    Stage stage = new Stage();
    Block block = new Block();
    MainStage mainStage;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ground = (FrameLayout) findViewById(R.id.stageLayout);

        buttonDown = (Button) findViewById(R.id.button_down);
        buttonUp = (Button) findViewById(R.id.button_up);
        buttonLeft = (Button) findViewById(R.id.button_left);
        buttonRight = (Button) findViewById(R.id.button_right);

        // stage 배열에 stage 객체에 정의된 배열값을 세팅한다
        setStage(1);
        // 뷰 객체를 생성한다
        mainStage = new MainStage(this,stage,block);
    }

    public void setStage(int stageLevel) {

        stage.setStage(stageLevel);



        block.setBlock();
    }

}
