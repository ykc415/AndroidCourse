package com.sunghyun.andriod.servicebasic01;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class MainService extends Service {

    private static final String TAG = "MainService";

    public MainService() {
    }


    @Override
    public void onCreate() {
        super.onCreate();

        Log.i(TAG, "================== onCreate");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Log.i(TAG, "================== onStartCommand");


        for(int i=0;i<100;i++) {

            try{
                Thread.sleep(100);
            }catch(Exception e){
            }
            Log.i(TAG,"Service Main ===============" + i);
        }


        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        Log.i(TAG, "================== onDestroy");
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
