package com.kodonho.android.fragmentbasic;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.kodonho.android.fragmentbasic.dummy.DummyContent;

import java.util.List;

public class MainActivity extends AppCompatActivity
        implements ItemFragment.OnListFragmentInteractionListener {

    private static final String TAG = "MainActivity";
    public static final List<DummyContent.DummyItem> datas = DummyContent.ITEMS;
    public static int position = 1;

    Fragment listFragment;
    FragmentTwo detailFragement;
    ViewPager pager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 가로뷰인지 세로뷰인지 결정해서 출력해준다.
        setContentView(R.layout.activity_main);

        listFragment =  new FragmentOne();
        detailFragement = new FragmentTwo();

        pager = (ViewPager) findViewById(R.id.pager);
        PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager(),listFragment, detailFragement);
        pager.setAdapter(adapter);


        //listFragment =ItemFragment.newInstance(1);
        // 프래그먼트로 값넘기기
//        Bundle args = new Bundle();
//        args.putString("key","값");
//        listFragment.setArguments(args);

        //detailFragement = new FragmentTwo();

        //setFragment();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        // 가로모드로 변경되었으면....
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {

            //세로모드로 변경되었으면
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {

        }
    }


    public ViewPager getPager() {
        return pager;
    }

    public Fragment getListFragment() {
        return listFragment;
    }

    public FragmentTwo getDetailFragement() {
        return detailFragement;
    }



    @Override
    public void onListFragmentInteraction(DummyContent.DummyItem item) {
        Log.i(TAG,"==========item="+item.content);
        detailFragement.setData(item);

    }
}
