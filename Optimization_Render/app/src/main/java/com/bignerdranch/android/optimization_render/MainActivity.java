package com.bignerdranch.android.optimization_render;

import android.os.Debug;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Debug.startMethodTracing("traceResult2016");

        final Thread another01 = new Thread() {

            @Override
            public void run() {
                print1000("another01");
            }
        };


        Thread another02 = new Thread() {

            @Override
            public void run() {
                print1000("another02");
            }
        };

        another01.start();
        another02.start();


    }





    @Override
    protected void onDestroy() {
        super.onDestroy();
        Debug.stopMethodTracing();
    }

    public void print1000(String tag) {
        for(int i = 0;i<1000;i++) {
            Log.i("PerformanceTest",tag+" i="+i);
        }
    }


}
