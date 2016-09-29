package com.sunghyun.andriod.basiclist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    ImageView img ;
    TextView title;
    TextView name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        img = (ImageView) findViewById(R.id.imageView);
        title = (TextView) findViewById(R.id.textView);
        name = (TextView) findViewById(R.id.textView2);

//
//        Bundle bundle = getIntent().getExtras();
//        RecyclerData data = (RecyclerData)bundle.getSerializable("object");

        Intent intent = getIntent();
        int position = intent.getExtras().getInt("position"); //리스트 데이터의 키값

        if(RecyclerAnimationActivity.datas != null) {
            RecyclerData data = RecyclerAnimationActivity.datas.get(position);

            img.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
            title.setText(data.title);
            name.setText(data.name);
            img.setImageResource(data.image);

        }

    }
}
