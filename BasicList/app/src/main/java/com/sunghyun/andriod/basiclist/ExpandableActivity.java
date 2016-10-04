package com.sunghyun.andriod.basiclist;

import android.content.Context;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.widget.ExpandableListView;

import java.util.ArrayList;

public class ExpandableActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expandable);

        ExpandableListView listView = (ExpandableListView) findViewById(R.id.expandableListView);

        // 아답터에 넘겨줄 데이터정의
        ArrayList<ExpandData> datas = new ArrayList<>();
        ExpandData data = new ExpandData();
        data.cityName = "서울";
        data.guNames.add("강동");
        data.guNames.add("강서");
        data.guNames.add("강남");
        data.guNames.add("강북");
        data.guNames.add("마포");
        data.guNames.add("서초");
        data.guNames.add("동작");
        datas.add(data);

        data = new ExpandData();
        data.cityName = "광주";
        data.guNames.add("광산");
        data.guNames.add("중구");
        data.guNames.add("북구");
        datas.add(data);

        data = new ExpandData();
        data.cityName = "부산";
        data.guNames.add("동래");
        data.guNames.add("중구");
        data.guNames.add("북구");
        data.guNames.add("동래");
        data.guNames.add("영도");
        data.guNames.add("해운대");

        datas.add(data);

        ExpandableAdapter adapter = new ExpandableAdapter(this, R.layout.expand_parent_item,
                R.layout.expand_child_item, datas);


        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int width = metrics.widthPixels;

        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR2)
            listView.setIndicatorBounds(width - GetPixelFromDips(50), width - GetPixelFromDips(10));

        else
            listView.setIndicatorBoundsRelative(width - GetPixelFromDips(50), width - GetPixelFromDips(10));



        listView.setAdapter(adapter);


        //dp 를 픽셀로 변환할 때
        int convertedPixel = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, 200, getResources().getDisplayMetrics()
        );

    }


    public int GetPixelFromDips(float pixels) {
        // Get the screen's density scale
        final float scale = getResources().getDisplayMetrics().density;
        // Convert the dps to pixels, based on density scale
        return (int) (pixels * scale + 0.5f);
    }

//    public int getPixelFromDisplay(float pixels) {
//        // 화면 밀도 스케일
//        final float scale = getResources().getDisplayMetrics().density;
//        // 컨버팅 dps > pixel - 화면밀도 스케일 기준으로
//        return (int) (pixels * scale + 0.5f);
//    }

    public int pxToDp(Context context, int px) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        int dp = Math.round(px / (metrics.xdpi / metrics.DENSITY_DEFAULT));
        return dp;
    }

    public int dpToPx(Context context, int dp) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        int px = Math.round(dp * (metrics.xdpi / metrics.DENSITY_DEFAULT));
        return px;
    }

}
