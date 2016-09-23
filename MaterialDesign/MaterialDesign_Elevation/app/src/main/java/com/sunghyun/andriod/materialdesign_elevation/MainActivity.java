package com.sunghyun.andriod.materialdesign_elevation;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


/*
    // Api level 21 이상에서 Material design 설정
    1. res/valudes/style.xml 의 Theme를 android:Theme.Material로 변경
    2. AndroidManifest.xml 의 aplication 의 theme속성을 변경된 Theme로 지정 (Default로 되어있다)
    3. Activity의 상속 클래스를 AppCompatActivity 에서 Activity로 변경

    // Api level 21 미만에서 설정 안됨
    1. 상속 받는 Activity를 원래대로 AppCompatActivity 로 변경
    2. style의 AppTheme 를 Theme.AppCompt 으로 변경
 */

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
