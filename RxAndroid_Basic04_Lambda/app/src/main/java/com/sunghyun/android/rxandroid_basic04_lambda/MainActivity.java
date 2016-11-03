package com.sunghyun.android.rxandroid_basic04_lambda;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import rx.Observable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG = MainActivity.class.getName();

    ArrayList<String> datas;

    Button btnLambda;
    Button btnMap;
    Button btnFlatMap;
    Button btnZip;
    TextView result;
    ListView listview;
    ArrayAdapter<String> adaptor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        datas = new ArrayList<>();

        btnLambda = (Button) findViewById(R.id.button_lambda);
        btnMap = (Button) findViewById(R.id.button_map);
        btnFlatMap = (Button) findViewById(R.id.button_flatmap);
        btnZip = (Button) findViewById(R.id.button_zip);
        result = (TextView) findViewById(R.id.textView);
        listview = (ListView) findViewById(R.id.listView);

        btnFlatMap.setOnClickListener(this);
        btnMap.setOnClickListener(this);
        btnZip.setOnClickListener(this);
        btnLambda.setOnClickListener(this);
        adaptor = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, datas);
        listview.setAdapter(adaptor);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.button_lambda:
                doLambda();
                break;
            case R.id.button_map:
                doMap();
                break;
            case R.id.button_flatmap:
                doFlatMap();
                break;
            case R.id.button_zip:
                break;
        }
    }

    public void doLambda() {
        Observable<String> observable = Observable.just("I am Lambda!");

        observable.subscribe(
                item -> result.setText(item),
                error -> error.printStackTrace(),
                () -> Log.i(TAG, "Completed")
        );
    }

    public void doMap() {
        Observable.from(new String[]{"dog", "bird", "chicken", "horse", "turtle", "rabbit"})
                .map(item -> "[" + item + "]")
                .subscribe(
                        item -> datas.add(item),
                        e -> e.printStackTrace(),
                        () -> adaptor.notifyDataSetChanged()
                );
    }

    public void doFlatMap() {
        Observable.from(new String[]{"dog", "bird", "chicken", "horse", "turtle", "rabbit"})
                .flatMap(item -> Observable.from(new String[]{ "name:"+item, item.getBytes()+"", "code:"+item}))
                .subscribe(
                        item -> datas.add(item),
                        e -> e.printStackTrace(),
                        () -> adaptor.notifyDataSetChanged()
                );
    }




}
