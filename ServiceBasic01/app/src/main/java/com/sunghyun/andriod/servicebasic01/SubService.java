package com.sunghyun.andriod.servicebasic01;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.util.Log;


public class SubService extends IntentService {

    public static final String TAG = "SubService";


    public SubService() { super("SubService"); }


    @Override
    protected void onHandleIntent(Intent intent) {
        Log.i(TAG, "================== onHandleIntent");
        for(int i=0;i<100;i++) {
            try{
                Thread.sleep(100);
            }catch(Exception e){

            }
            Log.i(TAG,"Service Sub ===============" + i);
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "================== onCreate");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "================== onDestroy");
    }
}
