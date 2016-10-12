package com.bignerdranch.android.fragmentbasic_tablayout_viewpager;

import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
/*
    1. 그래들에 disign 라이브러리 추가
    2. main xml 에 TabLayout , ViewPager 추가

 */

public class MainActivity extends AppCompatActivity implements OnFragmentInteractionListener{

    static final int FRAGMENT_COUNT = 4;
    HomeFragment home;
    MapFragment map;
    EtcFragment etc;
    Fragment settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        home = new HomeFragment();
        map = new MapFragment();
        etc = new EtcFragment();
        //settings = new SettingsFragment();
        settings = BlankFragment.newInstance("","");

        TabLayout tab = (TabLayout) findViewById(R.id.tab);

        tab.addTab(tab.newTab().setText("Home"));
        tab.addTab(tab.newTab().setText("Map"));
        tab.addTab(tab.newTab().setText("Etc"));
        tab.addTab(tab.newTab().setText("Settings"));

        final ViewPager pager = (ViewPager) findViewById(R.id.pager);
        PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager());
        pager.setAdapter(adapter);

        // 페이저가 변경되었을때 탭을 변경해주는 리스너
        pager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tab));


        tab.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(pager));
        // 탭에 페이저를 연결해주는 리스너
//        tab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
//            @Override
//            public void onTabSelected(TabLayout.Tab tab) {
//                pager.setCurrentItem(tab.getPosition());
//            }
//
//            @Override
//            public void onTabUnselected(TabLayout.Tab tab) {
//
//            }
//
//            @Override
//            public void onTabReselected(TabLayout.Tab tab) {
//
//            }
//        });
    }

    @Override
    public void onFragmentInteraction(String string) {
        Toast.makeText(this,string+"에서 클릭됨",Toast.LENGTH_SHORT).show();
    }

    class PagerAdapter extends FragmentStatePagerAdapter {

        public PagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment = null;
            switch (position) {
                case 0:
                    fragment = home;
                    break;
                case 1:
                    fragment = map;
                    break;
                case 2:
                    fragment = etc;
                    break;
                case 3:
                    fragment = settings;
                    break;
            }
            return fragment;
        }

        @Override
        public int getCount() {
            return FRAGMENT_COUNT;
        }
    }


}
