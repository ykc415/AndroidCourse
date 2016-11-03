package com.sunghyun.android.rxandroid_basic02;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func0;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    public static final String TAG = MainActivity.class.getName();

    ArrayList<String> datas;

    Button btnJust;
    Button btnFrom;
    Button btnDefer;
    TextView result;
    ListView listview;
    ArrayAdapter<String> adaptor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        datas = new ArrayList<>();

        btnJust = (Button) findViewById(R.id.button_just);
        btnFrom = (Button) findViewById(R.id.button_from);
        btnDefer = (Button) findViewById(R.id.button_defer);
        result = (TextView) findViewById(R.id.textView);
        listview = (ListView) findViewById(R.id.listView);

        btnDefer.setOnClickListener(this);
        btnFrom.setOnClickListener(this);
        btnJust.setOnClickListener(this);
        adaptor = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, datas);
        listview.setAdapter(adaptor);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_just:
                doJust();
                break;
            case R.id.button_from:
                doFrom();
                break;
            case R.id.button_defer:
                doDefer();
                break;
        }
    }

    // Java 데이터를 바로 Observable 객체로 변환 할수 있다
    private void doJust() {
        Observable<String> observable = Observable.just("dog");

        observable.subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                result.setText(s);
            }
        });
    }

    // 컬렉션 형태의 자바 객체로부터 옵저벼블을 생성한다.
    private void doFrom() {
        Observable<String> observable = Observable.from(new String[]{"dog", "bird", "chicken", "horse", "turtle", "rabbit"});

        observable.subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                datas.add(s);
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {

            }
        }, new Action0() {
            @Override
            public void call() {
                adaptor.notifyDataSetChanged();
            }
        });
    }

    // 지연 처리 함수를 제공하고
    // 호출 할때마다 옵저버블 객체를 매번 생성한다
    private void doDefer() {
        Observable<String> observable = Observable.defer(new Func0<Observable<String>>() {
            @Override
            public Observable<String> call() {
                return Observable.just("bird");
            }
        });

        observable.delaySubscription(1, TimeUnit.SECONDS).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                result.setText(s);
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {

            }
        }, new Action0() {
            @Override
            public void call() {
                Log.e(TAG, "Completed");
            }
        });
    }


}
