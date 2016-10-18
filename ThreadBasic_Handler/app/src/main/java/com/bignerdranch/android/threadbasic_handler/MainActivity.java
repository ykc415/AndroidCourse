package com.bignerdranch.android.threadbasic_handler;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static final int SET_RESULT = 1;

    TextView mTextView;
    Button mButtonStart, mButtonCall, mButtonStartHandler;
    SubThread thread;

    LooperHandler handlerThread;
    Handler subHandler;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        thread = new SubThread(mHandler);
        handlerThread = new LooperHandler(this, "abc");
        subHandler = handlerThread.looperHandler;


        mTextView = (TextView) findViewById(R.id.textView);
        mButtonStart = (Button) findViewById(R.id.button_start);
        mButtonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                thread.start();
            }
        });

        mButtonCall = (Button) findViewById(R.id.button_call);
        mButtonCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                thread.printLog();
            }
        });

        mButtonStartHandler = (Button) findViewById(R.id.button_starthandler);
        mButtonStartHandler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handlerThread.start();
                run();
                handlerThread.hideProgress();
            }
        });
    }

    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case SET_RESULT:
                    int temp = msg.arg1;
                    mTextView.setText("Result = " + temp);
                    break;
            }
        }
    };

    public void run() {
        try{
            int sum = 10;
            for(int i =0;i<30;i++) {
                sum += i;
                Thread.sleep(100);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    class LooperHandler extends HandlerThread {

        public static final int SHOW_PROGRESS = 1;
        public static final int HIDE_PROGRESS = 2;

        Handler looperHandler;
        Context context;
        ProgressDialog progress;

        public LooperHandler(Context context, String name) {
            super(name);
            this.context = context;
        }

        @Override
        protected void onLooperPrepared() {
            progress = new ProgressDialog(context);
            progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progress.setTitle("Progress Bar Title");
            progress.setMessage("Message");
            progress.setCancelable(false);
            progress.show();
        }

        private void showProgress() {
            Log.i("LooperHandler", "진행상태 보여주기~");
            progress.show();
        }


        private void hideProgress() {
            Log.i("LooperHandler", "진행상태 없애기~");
            progress.dismiss();
            try{
                Thread.sleep(1000);
            }catch (Exception e){

            }
            quit();
        }
    }

    class SubThread extends Thread {

        Handler mainHandler;
        Handler subHandler;

        public SubThread(Handler mhandler) {
            mainHandler = mhandler;
        }

        public void run() {
            int sum = 0;
            for(int i=0;i<10000;i++) {
                sum += i;
            }
            Message msg = new Message();
            msg.what = SET_RESULT;
            msg.arg1 = sum;
            mainHandler.sendMessage(msg);

        }

        public void printLog() {
            Log.i("SubThread", "called from the main thread");
        }



    }


}
