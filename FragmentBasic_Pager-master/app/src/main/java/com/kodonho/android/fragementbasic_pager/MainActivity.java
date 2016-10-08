package com.kodonho.android.fragementbasic_pager;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Fragment> datas = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewPager pager = (ViewPager) findViewById(R.id.pager);

        ListFragment bf = new ListFragment();
        bf.setPager(pager);
        datas.add(bf);
//        DetailFragment gf = new DetailFragment();
//        datas.add(gf);
        BlankFragment fragment = BlankFragment.newInstance("제목", "내용");
        datas.add(fragment);

        PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager(), datas);
        pager.setAdapter(adapter);
    }

    private class PagerAdapter extends FragmentStatePagerAdapter{

        ArrayList<Fragment> datas = new ArrayList<>();

        // 프래그먼트 아답터를 생성하기 위해서는 프래그먼트 매니저를 생성자에 넘겨주고
        // 부모아답터가 초기화 하여야 한다
        public PagerAdapter(FragmentManager manager, ArrayList<Fragment> datas){
            super(manager);
            this.datas = datas;
        }

        @Override
        public Fragment getItem(int position) {
            return datas.get(position);
        }

        @Override
        public int getCount() {
            return datas.size();
        }
    }
}
