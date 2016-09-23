package com.sunghyun.andriod.testservice;

import android.app.IntentService;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.Context;
import android.content.IntentSender;
import android.util.Log;

public class MyIntentService extends IntentService {
    public static final String TAG = "MyIntentService";
    public static final String RESULT = "result";

    private static int angle = 0 ; // 다음번 눌렀을때 0으로 되돌아가는걸 막기위하여 static 으로 선언

    public MyIntentService() { super("MyIntentService"); }


    @Override
    protected void onHandleIntent(Intent intent) {
        for(int i=0;i<10;i++) {
            try {
                angle = angle + 36;

                Intent result = new Intent();
                result.putExtra(RESULT, angle); // angle을 RESULT와 함께 넣어줌
                PendingIntent reply = intent.getParcelableExtra(MainActivity.KEYCODE);// PendingIntent 를 받음
                reply.send(this, MainActivity.REQUEST_CODE, result); //REQUEST_CODE 와 결과값을 send

                Thread.sleep(1000);

            } catch (Exception e) {

            }
            Log.i(TAG, i + "angle" + angle);
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
