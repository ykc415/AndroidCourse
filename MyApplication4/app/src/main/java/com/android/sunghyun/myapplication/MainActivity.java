package com.android.sunghyun.myapplication;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    static final String TAG = MainActivity.class.getSimpleName();


    int[] before = { 5 , 7, 2, 1, 6, 9, 4 };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Log.i(TAG, "정렬전 : " + before[0]+ before[1]+ before[2]+ before[3]+ before[4]+ before[5]+ before[6]);


    }

    @Override
    protected void onResume() {
        super.onResume();

        bubbleSort(before);


        Log.i(TAG, "정렬후 : " + before[0]+ before[1]+ before[2]+ before[3]+ before[4]+ before[5]+ before[6]);



    }

    int[] bubbleSort(int[] arr) {

        new Handler() {
            
        }
        int temp;
        for (int j = 0; j<arr.length-1;) {
            for (int i = 0; i < arr.length - 1; i++) {
                if (arr[i] < arr[i + 1]) {
                    temp = arr[i];
                    arr[i] = arr[i + 1];
                    arr[i + 1] = temp;
                }
            }
        }

        return arr;
    }
}
