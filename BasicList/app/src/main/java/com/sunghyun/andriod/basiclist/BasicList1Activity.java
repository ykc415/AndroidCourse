package com.sunghyun.andriod.basiclist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

public class BasicList1Activity extends AppCompatActivity {

    String datas[] = {"백향목", "김동진", "김태원", "임재민", "김도형", "석주영", "장홍석", "김해든"};

    // 데이터를 담은 adapter를 받는 리스트뷰
    ListView listView;

    // 데이터를 담을 객체
    ArrayAdapter<String> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_list1);

                                        // 1. 컨텍스트  2. 아이템 레이아웃             3. 데이터
        adapter = new ArrayAdapter<String>(this ,android.R.layout.simple_list_item_checked, datas);
        /*
          2. 아이템 레이아웃 종류
          simple_list_item2                 텍스트뷰 두개로 구성
          simple_list_item_checked          끝에 체크박스가 포함됨
          simple_list_item_single_choice    끝에 라디오버튼 생성
          simple_list_item_mulitple_choice  끝에 체크박스 생성

         */



        listView = (ListView) findViewById(R.id.listView);
        // 선택모드 활성화 - ListView.XXX_MODE  
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        listView.setAdapter(adapter);

    }
}
