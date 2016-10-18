package com.bignerdranch.android.viewbasic_customviewwithpath;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity {

    CustomView cv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cv = new CustomView(this);
        FrameLayout ground = (FrameLayout) findViewById(R.id.ground);

        ground.addView(cv);

        Button button  = (Button)findViewById(R.id.btnClear);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cv.reset();
            }
        });

    }
}
