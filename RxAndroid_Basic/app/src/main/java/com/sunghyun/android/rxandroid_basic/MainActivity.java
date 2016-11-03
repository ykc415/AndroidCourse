package com.sunghyun.android.rxandroid_basic;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action0;
import rx.functions.Action1;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Observable<String>  observable =
                Observable.create(new Observable.OnSubscribe<String>() {
                    @Override
                    public void call(Subscriber<? super String> subscriber) {
                        subscriber.onNext("Hello RxAndroid !!");
                        subscriber.onNext("Nice to see you !!");

                        subscriber.onCompleted();
                    }
                });


        /*
            onNext - 새로운 데이터를 전달한다.
            onCompleted - 스트림의 종료.
            onError - 에러 신호를 전달한다
         */
        observable.subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {
                        Log.e(TAG, "complete!");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "error: " + e.getMessage());
                    }

                    @Override
                    public void onNext(String text) {
                        Log.e(TAG, "next Value: " + text);
                    }
                });

        observable.subscribe(new Action1<String>() { // onNext
            @Override
            public void call(String text) {
                ((TextView) findViewById(R.id.textView)).setText(text);
            }
        }, new Action1<Throwable>() { // onError
            @Override
            public void call(Throwable throwable) {

            }
        }, new Action0() {
            @Override
            public void call() { // onComplete      

            }
        });
    }
}
