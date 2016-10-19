package com.sunghyun.nmn.sqlite_memopad;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.sunghyun.nmn.sqlite_memopad.domain.Memo;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DBHelper dbHelper = new DBHelper(this);
        Memo memo = new Memo();
        memo.contents ="메모내용";
        memo.ndate = memo.getTimeStamp();
        memo.image = "이미지";
        Date date = new Date(memo.ndate);
        Log.i("TIME", "time="+date);

        dbHelper.dbInsert(memo);

        ArrayList<Memo> datas = dbHelper.dbSelectAll();
        for(Memo data:datas) {
            Date date2 = new Date(data.ndate);
            Log.i("Memo", "no=" +data.no);
            Log.i("Memo", "contents=" +data.contents);
            Log.i("Memo", "ndate=" +date2);
            Log.i("Memo", "image=" +data.image);

        }

    }
}
