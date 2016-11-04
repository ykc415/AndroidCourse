package com.nullproject.android.rxandroid_basic05_filter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import rx.Observable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG = MainActivity.class.getName();


    Integer dataset[] = {1,2,3,1,4,5,3,6,7,8,7,5,9};

    Button btnFilter,btnFor,btnLast,btnDistinct,btnTake,btnGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnFilter = (Button) findViewById(R.id.button_filter);
        btnFor = (Button) findViewById(R.id.button_foreach);
        btnLast = (Button) findViewById(R.id.button_last);
        btnDistinct = (Button) findViewById(R.id.button_distinct);
        btnTake = (Button) findViewById(R.id.button_take);
        btnGroup = (Button) findViewById(R.id.button_groupby);

        btnFilter.setOnClickListener(this);
        btnFor.setOnClickListener(this);
        btnLast.setOnClickListener(this);
        btnDistinct.setOnClickListener(this);
        btnTake.setOnClickListener(this);
        btnGroup.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_filter:
                filter();
                break;
            case R.id.button_foreach:
                foreach();
                break;
            case R.id.button_last:
                last();
                break;
            case R.id.button_distinct:
                distinct();
                break;
            case R.id.button_take:
                take();
                break;
            case R.id.button_groupby:
                groupby();
                break;
            case R.id.button_first:
                first();
                break;
        }
    }


    private void first() {
        Observable.from(dataset)
                .first()
                .subscribe(
                        result -> Print.print(result)
                );
    }

    private void filter() {
        Observable.from(dataset)
        .filter(item -> item % 2 == 0)
        .subscribe(
             result -> Print.print(result)
        );
    }

    private void foreach() {
        Observable.from(dataset)
                .forEach(
                        result -> Print.print(result)
                );
    }

    private void last() {
        Observable.from(dataset)
                .last()
                .subscribe(
                        result -> Print.print(result)
                );
    }

    private void distinct() {
        Observable.from(dataset)
                .distinct()
                .subscribe(
                        result -> Print.print(result)
                );
    }

    private void take() {
        Observable.from(dataset)
                .distinct()
                .subscribe(
                        result -> Print.print(result)
                );
    }

    private void groupby() {
        Observable.from(dataset)
                .groupBy(item -> item%2 == 0)
                .subscribe(
                        grouped -> grouped.toList()
                                .subscribe(
                                        items -> Log.e(TAG, items +" 짝수"+ grouped.getKey())
                                ));
    }
}

class Print {
    public static final String TAG = Print.class.getName();
    public static void print(int item) {
        Log.i(TAG,"item = " + item);
    }
}

