package com.bignerdranch.android.fragmentbasic03;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Fragment listFragment;
    Fragment detailFragment;

    public ArrayList<ListData> datas = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        for(int i =0 ;i<20;i++) {
            ListData data = new ListData();
            data.title = i+ " item Title";
            data.contents = makeDetails(i);
            datas.add(data);
        }

        listFragment = new FragmentOne();
        detailFragment = new FragmentTwo();


    }

    public ArrayList<ListData> getDatas() {
        return datas;
    }
}
