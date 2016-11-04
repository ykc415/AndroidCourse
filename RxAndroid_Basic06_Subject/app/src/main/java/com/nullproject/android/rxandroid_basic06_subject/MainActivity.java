package com.nullproject.android.rxandroid_basic06_subject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import rx.subjects.AsyncSubject;
import rx.subjects.BehaviorSubject;
import rx.subjects.PublishSubject;
import rx.subjects.ReplaySubject;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button button1, button2, button3, button4, button5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button1 = (Button) findViewById(R.id.button_publish);
        button2 = (Button) findViewById(R.id.button_behavior);
        button3 = (Button) findViewById(R.id.button_replay);
        button4 = (Button) findViewById(R.id.button_async);
        button5 = (Button) findViewById(R.id.button_asyncComplete);

        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        button5.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.button_publish:
                publishSubject();
                break;
            case R.id.button_behavior:
                behaviorSubject();
                break;
            case R.id.button_replay:
                replaySubject();
                break;
            case R.id.button_async:
                asyncSubject();
                break;
            case R.id.button_asyncComplete:
                asyncCompleteSubject();
                break;
        }
    }

    // 구독한 시점부터 발행된 아이템을 받는다.
    private void publishSubject() {
        PublishSubject<String> subject = PublishSubject.create();

        subject.onNext("A");
        subject.onNext("B");
        subject.onNext("C");

        subject.subscribe(
                item-> Log.e("Publish", "item = " + item)
        );

        subject.onNext("D");
        subject.onNext("E");
    }


    // 가장 최근에 관찰된 아이템부터 구독한다.
    private void behaviorSubject() {
        BehaviorSubject<String> subject = BehaviorSubject.create();

        subject.onNext("A");
        subject.onNext("B");
        subject.onNext("C");

        subject.subscribe(
                item-> Log.e("Behavior", "item = " + item)
        );

        subject.onNext("D");
        subject.onNext("E");
    }

    private void replaySubject() {
        ReplaySubject<String> subject = ReplaySubject.create();

        subject.onNext("A");
        subject.onNext("B");
        subject.onNext("C");

        subject.subscribe(
                item-> Log.e("Replay", "item = " + item)
        );

        subject.onNext("D");
        subject.onNext("E");
    }

    private void asyncSubject() {
        AsyncSubject<String> subject = AsyncSubject.create();

        subject.onNext("A");
        subject.onNext("B");
        subject.onNext("C");

        subject.subscribe(
                item-> Log.e("Async", "item = " + item)
        );

        subject.onNext("D");
        subject.onNext("E");
    }

    private void asyncCompleteSubject() {
        AsyncSubject<String> subject = AsyncSubject.create();

        subject.onNext("A");
        subject.onNext("B");
        subject.onNext("C");

        subject.subscribe(
                item-> Log.e("AsyncComplete", "item = " + item)
        );

        subject.onNext("D");
        subject.onNext("E");
        subject.onCompleted();
    }

}
