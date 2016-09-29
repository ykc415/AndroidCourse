package com.sunghyun.andriod.basiclist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import java.util.ArrayList;

public class RecyclerCardActivity extends AppCompatActivity {


    public static ArrayList<RecyclerData> datas = null;

    RecyclerView rv ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_card);

        datas = new ArrayList<>();

        for(int i=0;i<100;i++) {
            RecyclerData data = new RecyclerData();
            data.title = i + " Hey Brother";
            data.name = "Avcii";
            data.image = R.mipmap.avicii;

            datas.add(data);
        }

        rv = (RecyclerView) findViewById(R.id.recyclerCardView);
        RecyclerCardAdapter adapter = new RecyclerCardAdapter(this,datas,R.layout.activity_recycler_card_item);
        rv.setAdapter(adapter);


        // 수직 수평 스크롤이 가능
        //RecyclerView.LayoutManager manager = new LinearLayoutManager(this);
        // 그리드 타입
        //RecyclerView.LayoutManager manager = new GridLayoutManager(this, 3);
        // StaggerdGrid 타입
        RecyclerView.LayoutManager manager = new StaggeredGridLayoutManager(3, OrientationHelper.HORIZONTAL);
        rv.setLayoutManager(manager);
    }
}
