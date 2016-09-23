package com.sunghyun.andriod.materialdesign_viewanimation01;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnAlpha;
    Button btnRotate;
    Button btnScale;
    Button btnTrans;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btnAlpha = (Button)findViewById(R.id.button);
        btnRotate = (Button)findViewById(R.id.rotate);
        btnScale = (Button)findViewById(R.id.scale);
        btnTrans = (Button)findViewById(R.id.translate);


        btnAlpha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 1. 미리 정의된 애니메이션 xml을 로드한다.
                Animation animation = AnimationUtils.loadAnimation(MainActivity.this,R.anim.alpha);
                // 2. 애니메이션을 뷰에 적용하고 실행한다
                btnAlpha.startAnimation(animation);
            }
        });


        btnRotate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 1. 미리 정의된 애니메이션 xml을 로드한다.
                Animation animation = AnimationUtils.loadAnimation(MainActivity.this,R.anim.rotate);
                // 2. 애니메이션을 뷰에 적용하고 실행한다
                btnRotate.startAnimation(animation);
            }
        });


        btnScale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 1. 미리 정의된 애니메이션 xml을 로드한다.
                Animation animation = AnimationUtils.loadAnimation(MainActivity.this,R.anim.scale);
                // 2. 애니메이션을 뷰에 적용하고 실행한다
                btnScale.startAnimation(animation);
            }
        });


        btnTrans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 1. 미리 정의된 애니메이션 xml을 로드한다.
                Animation animation = AnimationUtils.loadAnimation(MainActivity.this,R.anim.translate);
                // 2. 애니메이션을 뷰에 적용하고 실행한다
                btnTrans.startAnimation(animation);
            }
        });


    }


}
