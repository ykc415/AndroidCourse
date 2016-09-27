package com.sunghyun.andriod.basicwidget;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TabHost;

public class TabActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab);

        TabHost tabHost = (TabHost) findViewById(R.id.tabHost);
        tabHost.setup();

        //Tab 1
        TabHost.TabSpec spec1 = tabHost.newTabSpec("Tab One");
        // 탭을 눌렀을 때 호출되는 View
        spec1.setContent(R.id.tab1);
        // 탭의 이름
        spec1.setIndicator("Tab 001");
        // 탭호스트에 탭을 담아준다


        //Tab 2
        TabHost.TabSpec spec2 = tabHost.newTabSpec("Tab Two");
        spec2.setContent(R.id.tab2);
        spec2.setIndicator("Tab 002");

        //Tab 3
        TabHost.TabSpec spec3 = tabHost.newTabSpec("Tab Three");
        spec3.setContent(R.id.tab3);
        spec3.setIndicator("Tab 003");

        tabHost.addTab(spec1);
        tabHost.addTab(spec2);
        tabHost.addTab(spec3);
    }
}
