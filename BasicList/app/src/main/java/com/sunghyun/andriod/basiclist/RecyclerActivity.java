package com.sunghyun.andriod.basiclist;

import android.os.Parcel;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerActivity extends AppCompatActivity {

    public static ArrayList<RecyclerData> datas = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);

        datas = new ArrayList<>();

        for(int i=0;i<100;i++) {

            RecyclerData data = new RecyclerData();
            String str = i+ " Hey Brother";
            data.title = str;
            data.name = "Avcii";
            data.image = R.mipmap.avicii;

            datas.add(data);
        }

        RecyclerView listView = (RecyclerView) findViewById(R.id.recycerVeiw);
        RecyclerAdapter adapter = new RecyclerAdapter(this,datas, R.layout.recycler_item);
        listView.setAdapter(adapter);
        listView.setLayoutManager(new LinearLayoutManager(this));

    }
}
