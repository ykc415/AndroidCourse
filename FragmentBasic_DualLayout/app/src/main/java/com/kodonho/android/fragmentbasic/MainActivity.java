package com.kodonho.android.fragmentbasic;

import android.content.res.Configuration;
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

    //Fragment listFragment;
    FragmentTwo detailFragement;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 1. create 에서 프래그먼트 스택 예외처리 하기
        FragmentManager manager = getSupportFragmentManager();
        int stackCount = manager.getBackStackEntryCount();
        if(stackCount > 0) {
            manager.popBackStack();
        }

        // 2. 예외처리후 세팅해준다
        setContentView(R.layout.activity_main);

        // 3. 선택된 프래그먼트가 있다면 재생성
        if(position > -1){
            goDetail();
        }
    }

    public void goDetail(){
        FragmentManager manager = getSupportFragmentManager();

        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
            FragmentTransaction transaction = manager.beginTransaction();
            detailFragement = new FragmentTwo();
            transaction.replace(R.id.fragment, detailFragement);
            transaction.addToBackStack(null);
            transaction.commit();
        }else{
            detailFragement = (FragmentTwo) manager.findFragmentById(R.id.fragmentDetail);
            if(detailFragement != null){
                detailFragement.setData(datas.get(position));
            }
        }
    }

    @Override
    public void onListFragmentInteraction(DummyContent.DummyItem item) {
        Log.i(TAG,"==========item="+item.content);
        goDetail();
    }
}
