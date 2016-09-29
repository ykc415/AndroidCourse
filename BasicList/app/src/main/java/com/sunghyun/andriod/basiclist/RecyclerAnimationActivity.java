package com.sunghyun.andriod.basiclist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import java.util.ArrayList;

public class RecyclerAnimationActivity extends AppCompatActivity {

    // 1. Recycler뷰를 현재 액티비티의 메인레이아웃에 만든다
    // 2. Recycler뷰에 들어갈 아이템 레이아웃을 만든다
    //   들어갈 데이터는 정의되어 있고
    // 3. Adapter를 만든다
    // 4. Recycler뷰에 아답터를 세팅한다
    // 5. Recycler뷰에 레이아웃 매니저를 지정한다.

    public static ArrayList<RecyclerData> datas = null;

    RecyclerView rv ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_animation);

        datas = new ArrayList<>();

        for(int i=0;i<100;i++) {
            RecyclerData data = new RecyclerData();
            data.title = i + " Hey Brother";
            data.name = "Avcii";
            data.image = R.mipmap.avicii;

            datas.add(data);
        }

        rv = (RecyclerView) findViewById(R.id.recycerVeiw);
        RecyclerAdapter adapter = new RecyclerAdapter(this,datas,R.layout.recycler_item);
        rv.setAdapter(adapter);
        rv.setLayoutManager(new LinearLayoutManager(this));
    }
}
