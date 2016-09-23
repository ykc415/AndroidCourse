package com.sunghyun.andriod.activitybasic01;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



    }
    // Activity를 호출하는 함수
    // - 버튼에서 호출하기 위해 파라미터로 View 를 설정해야만 한다.
    public void openActivity(View v) {
        // Activity 를 호출하는 로직
        // 1. 인텐트를 생성            컨텍스트 , 호출할 액티비티 클래스명
        Intent intent = new Intent(this, Activity2.class);

        // 2. 생성된 인텐트를 시스템에 넘겨서 실행
        startActivity(intent);




    }


    public void openMe(View v) {

        Intent intent = new Intent(this, MainActivity.class);

        startActivity(intent);
    }

    // 전화걸기
    public void openCall(View v) {
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:114"));
        startActivity(intent);
    }
    // 문자보내기
    public void openSMS(View v) {
        Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:01012345678"));
        startActivity(intent);
    }
    // 네이버
    public void openNaver(View v) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://naver.com"));
        startActivity(intent);

    }
}
