package com.kodonho.android.fragmentbasic;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.kodonho.android.fragmentbasic.dummy.DummyContent;

import java.util.List;

public class MainActivity extends AppCompatActivity implements ItemFragment.OnListFragmentInteractionListener {

    private static final String TAG = "MainActivity";
    public static final List<DummyContent.DummyItem> datas = DummyContent.ITEMS;
    public static int position = -1;

    Fragment listFragment;
    FragmentTwo detailFragement;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listFragment =  new FragmentOne();
        //listFragment =ItemFragment.newInstance(1);
        // 프래그먼트로 값넘기기
//        Bundle args = new Bundle();
//        args.putString("key","값");
//        listFragment.setArguments(args);

        detailFragement = new FragmentTwo();

        setFragment();
    }

    public void setFragment(){
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.fragment, listFragment);
        transaction.commit();
    }

    public void goDetail(){
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fragment, detailFragement);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void onListFragmentInteraction(DummyContent.DummyItem item) {
        Log.i(TAG,"==========item="+item.content);
        detailFragement.setData(item);
        goDetail();
    }
}
