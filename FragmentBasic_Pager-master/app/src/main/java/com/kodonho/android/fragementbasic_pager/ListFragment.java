package com.kodonho.android.fragementbasic_pager;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

public class ListFragment extends Fragment {

    int TargetPager = 1;

    ViewPager viewPager;

    public ListFragment() {

    }

    public void setPager(ViewPager pager){
        viewPager=pager;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_blank, container, false);
        ListView listView = (ListView)view.findViewById(R.id.listView);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // 리스트가 클릭되면 항상 두번째 페이저로 보낸다
                viewPager.setCurrentItem(TargetPager);
                // 상세 프래그먼트에 들어갈 데이터를 세팅한다
                // 여기에서 프래그먼트를 호출하등가... mainAcitivity 에 있는 어떤 함수를 호출
            }
        });
        return view;
    }
}
