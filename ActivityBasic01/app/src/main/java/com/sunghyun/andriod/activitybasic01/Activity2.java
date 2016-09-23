package com.sunghyun.andriod.activitybasic01;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Activity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
    }

    public void nextActivity(View v) {
        // Activity 를 호출하는 로직
        // 1. 인텐트를 생성            컨텍스트 , 호출할 액티비티 클래스명
        Intent intent = new Intent(this, Activity3.class);

        // 2. 생성된 인텐트를 시스템에 넘겨서 실행
        startActivity(intent);



    }
}
