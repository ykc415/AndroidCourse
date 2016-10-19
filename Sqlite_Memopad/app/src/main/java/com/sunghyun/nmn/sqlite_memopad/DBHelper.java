package com.sunghyun.nmn.sqlite_memopad;

import android.content.Context;
import android.database.ContentObservable;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.ArrayAdapter;

import com.sunghyun.nmn.sqlite_memopad.domain.Memo;

import java.util.ArrayList;

/**
 * Created by YKC on 2016. 10. 13..
 */

public class DBHelper extends SQLiteOpenHelper {

    private final static String DB_NAME = "memo.sqlite";
    private final static int DB_VERSION = 3;

    // C-insert R-select1개 R-selete전체 U-update D-delete 쿼리를 만들어준다
    public void dbInsert(Memo memo) {
        // db를 연결하고
        SQLiteDatabase db = getWritableDatabase();
        // 쿼리를 생성하고
        String query = " insert into memo(contents, ndate, image)"
                     + " values('"+memo.contents+"'," +
                               "'"+memo.ndate+"'," +
                               "'"+memo.image+"')";
        db.execSQL(query);
        db.close(); // 닫는다
    }

    public Memo dbSelectOne(int no) { // select 는 table의 key를 기준으로 값을 받는다
        Memo memo = new Memo();

        return memo;
    }

    public ArrayList<Memo> dbSelectAll() {
        ArrayList<Memo> datas = new ArrayList<>();
        // 1.디비를 open 일기모드로
        SQLiteDatabase db = getReadableDatabase();
        // 2.select 쿼리 작성
        String query = "select * from memo";
        // 3.쿼리를 실행해서 cursor에  담고
        Cursor cursor = db.rawQuery(query,null);
        // 4.커서에 담긴 데이터를 while문을 돌면서 꺼내고

        while(cursor.moveToNext()) {
            // 5.1 메모 데이터 단위로 커서에서 꺼내와서 담아준다.
            Memo data = new Memo();
            int idx = cursor.getColumnIndex("no");
            data.no = cursor.getInt(idx);
            idx = cursor.getColumnIndex("contents");
            data.contents = cursor.getString(idx);
            idx = cursor.getColumnIndex("ndate");
            data.ndate = cursor.getLong(idx);
            idx = cursor.getColumnIndex("image");
            data.image = cursor.getString(idx);
            datas.add(data);
        }
        cursor.close();
        db.close();
        return datas;
    }

    public void dbUpdate(Memo memo) {

    }

    public void dbDelete(Memo memo) {

    }


    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {                         // 자동 증가
//        // db version 1 - 최초등록                   primary ket 다음에 autoincrement를 써야한다
//        String scheme_01 = "create table memo(no integer primary key autoincrement not null" +
//                " ,contents text not null" +
//                " ,ndate integer not null)";
//        db.execSQL(scheme_01);

        String scheme_02 = "create table memo(no integer primary key autoincrement not null" +
                " ,contents text not null" +
                " ,image text" +              //image 컬럼 추가
                " ,ndate integer not null)";
        db.execSQL(scheme_02);

        //백업데이터복원
        for(Memo memo:backupDatas) {
            String query = " insert into memo(contents,ndate)"
                    + " values('"+memo.contents+"','"+memo.ndate+"') ";
            db.execSQL(query);
        }

    }


    ArrayList<Memo> backupDatas = new ArrayList<>();

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
                                           // 이전데이터버전  //  현재버전(업그레이드되는)
        // 이전 데이터베이스 버전에 따라 처리방식이 달라진다
        if(oldVersion == 1) {
            String query = "select no,contents,ndate from memo order by no";
            Cursor cursor = db.rawQuery(query,null);
            while(cursor.moveToNext()) {
                Memo data = new Memo();
                data.no = cursor.getInt(0);
                data.contents = cursor.getString(1);
                data.ndate = cursor.getLong(2);
                backupDatas.add(data);
            }

            // 컬럼이 3개
        }


        // db version 2 - upgrade 2016-10-13 14:08
        String scheme_01_drop = "drop table memo";
        db.execSQL(scheme_01_drop);

        onCreate(db);
    }






}
