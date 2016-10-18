package com.bignerdranch.android.customview_moverectwiththread;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity {

    FrameLayout ground;
    Button btnAnimate;
    CustomView cv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ground = (FrameLayout) findViewById(R.id.ground);
        cv = new CustomView(this);

        ground.addView(cv);

        btnAnimate = (Button)findViewById(R.id.button);
        btnAnimate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomThread thread = new CustomThread(cv);
                thread.start();
            }
        });
    }
}
