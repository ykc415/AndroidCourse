package com.sunghyun.android.firebase_message;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {


    public static final int SET_RESULT = 1;

    public static Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyFirebaseInstanceIDService service = new MyFirebaseInstanceIDService();
                service.onTokenRefresh();
            }
        });




    }

    static Handler mHandler = new Handler() ;


    public static void changeButton(final String str) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                button.setText(str);
            }
        };

        mHandler.post(runnable);
    }


}
