package com.bignerdranch.android.optimization_render;

import android.os.Debug;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Debug.startMethodTracing("traceResult");

        print100000();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Debug.stopMethodTracing();
    }

    public void print100000() {
        for(int i = 0;i<100000;i++) {
            System.out.println("i="+i);
        }
    }


}
