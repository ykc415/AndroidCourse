package com.kodonho.android.sqlitebasic_bbs;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.io.File;

public class MainActivity extends AppCompatActivity implements  OnFragmentListener{
    ListFragment lf; // 목록 프래그먼트
    EditFragment ef; // 쓰기 프래그먼트

    ViewPager pager;

    // 앱 초기화 > onCreate에서 호출
    private void init(){
        // DB 파일이 internal files 디렉토리에 있는지 여부 검사
        File file = new File(DataUtil.getFullpath(this,DataUtil.DB_NAME));
        // 파일이 없을때만 db 파일 생성
        if(!file.exists())
            DataUtil.assetToDisk(this,DataUtil.DB_NAME);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //앱 초기화 - DB 생성 등...
        init();

        setContentView(R.layout.activity_main);

        lf = new ListFragment();
        ef = new EditFragment();

        pager = (ViewPager) findViewById(R.id.pager);
        CustomAdapter adapter = new CustomAdapter(getSupportFragmentManager());

        pager.setAdapter(adapter);
    }

    public final static int ACTION_WRITE = 0;
    public final static int ACTION_CANCEL = 1;
    public final static int ACTION_GOLIST = 2;
    public final static int ACTION_GOEDIT = 3;
    public final static int ACTION_GOLIST_WITH_REFRESH = 4;

    @Override
    public void action(int flag) {
        switch (flag){
            case ACTION_WRITE:
                pager.setCurrentItem(1);
                break;
            case ACTION_CANCEL:
                pager.setCurrentItem(0);
                break;
            case ACTION_GOLIST:
                pager.setCurrentItem(0);
                break;
            case ACTION_GOEDIT:
                pager.setCurrentItem(1);
                break;
            case ACTION_GOLIST_WITH_REFRESH:
                // list에 새로운 데이터를 추가하고
                lf.setList(lf.listCount);
                // 화면을 갱신한다
                lf.adapter.notifyDataSetChanged();
                // 페이저를 리스트로 이동
                pager.setCurrentItem(0);
                break;
        }
    }

    // 리스트의 아이템을 클릭하면 호출되는 함수
    @Override
    public void actionEdit(int bbsno){
        ef.setData(bbsno);
        action(ACTION_GOEDIT);
    }

    /* ----------------------------------------------
      여기서 부터 ViewPager용 Adapter
    ---------------------------------------------- */
    class CustomAdapter extends FragmentStatePagerAdapter{
        public CustomAdapter(FragmentManager fm) {
            super(fm);
        }
        @Override
        public Fragment getItem(int position) {
            Fragment fragment = null;
            // 페이저의 첫번째 화면에는 목록 프래그먼트
            //          두번째 화면에는 상세 프래그먼트를 넘겨준다
            switch (position){
                case 0: fragment=lf; break;
                case 1: fragment=ef; break;
            }
            return fragment;
        }
        @Override
        public int getCount() {
            return 2;
        }
    }
}
