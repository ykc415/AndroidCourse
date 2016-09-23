package com.sunghyun.andriod.broadcastreciever01;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class MyReceiver extends BroadcastReceiver {

    static final String BROADCAST_ACTION = "com.sunghyun.andriod.MESSAGE";

    public MyReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent.getAction().equals(BROADCAST_ACTION)) {

            Bundle bundle = intent.getExtras();
            String str = bundle.getCharSequence("msg").toString();
            Log.i("asdf",str);
            // Activity 화면을 띄워준다.
            Intent i = new Intent(context,MapsActivity.class);
            i.addFlags(i.FLAG_ACTIVITY_NEW_TASK | i.FLAG_ACTIVITY_CLEAR_TOP);

            context.startActivity(i);

        }

    }
}
