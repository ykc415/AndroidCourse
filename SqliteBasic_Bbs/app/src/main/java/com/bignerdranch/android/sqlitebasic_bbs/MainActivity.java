package com.bignerdranch.android.sqlitebasic_bbs;

import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements OnFragmentInteractionListener{

    public final  static String DB_NAME = "sqlite.db";

    public final static int ACTION_WRITE = 0;
    public final static int ACTION_SAVE = 1;
    public final static int ACTION_CANCEL = 2;
    public final static int ACTION_MODIFY = 3;
    public final static int ACTION_DELETE = 4;
    public final static int LIST_FRAGMENT = 0;
    public final static int EDIT_FRAGMENT = 1;

    private ArrayList<BbsData> datas = new ArrayList<>();


    private EditFragment editfragment; // 쓰기
    private ListFragment listfragment; // 목록
    private ViewPager pager;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    private void init() {
        File file = new File(getFullpath("sqlite.db"));
        if(!file.exists())
            assetToDisk("sqlite.db");

        openDB();
        selectDB();

        editfragment = new EditFragment();
        listfragment = new ListFragment();

        pager = (ViewPager) findViewById(R.id.pager);
        CustomPagerAdapter adapter = new CustomPagerAdapter(getSupportFragmentManager());
        pager.setAdapter(adapter);



    }

    private void openDB() {
        // 데이터베이스를 연결하는 Api
        db = SQLiteDatabase.openDatabase(
                getFullpath("sqlite.db"),null,0);
        // 0: 쓰기가능 1: read only
    }

    private void insertDB() {
        BbsData data = datas.get(datas.size()-1);
        if(db != null) {
            // 쿼리를 실행해준다 select 문을 제외한 모든 쿼리에 사용
            db.execSQL("insert into bbs(no,title,name,contents,ndate) " +
                    "values("+ "'" + data.no + "'" + ","
                             + "'" + data.title + "'" +","
                             + "'" + data.name + "'" + ","
                             + "'" + data.contents + "'" + ","
                             + "'" + data.ndate + "'" +")"
            );
            // 쿼리를 실행 후 결과값을 Cursor 리턴해준다 즉 select문에 사용
            // db.rawQuery("",null);
        }
    }

    private void selectDB() {
        if(db != null) {
            Cursor cursor = db.rawQuery("select * from bbs", null);
            while(cursor.moveToNext()) {
                int idIdx = cursor.getColumnIndex("no");
                int no = cursor.getInt(idIdx);

                idIdx = cursor.getColumnIndex("title");
                String title = cursor.getString(idIdx);

                idIdx = cursor.getColumnIndex("name");
                String name = cursor.getString(idIdx);

                idIdx = cursor.getColumnIndex("contents");
                String contents = cursor.getString(idIdx);

                idIdx = cursor.getColumnIndex("ndate");
                String ndate = cursor.getString(idIdx);

                BbsData data = new BbsData();
                data.no = no;
                data.title = title;
                data.name = name;
                data.contents = contents;
                data.ndate = ndate;

                datas.add(data);
                //result.invalidate();

            }
        }
    }

    private void updateDB(int position) {
        BbsData data = getData(position);
        if(db != null) {
            // 쿼리를 실행해준다 select 문을 제외한 모든 쿼리에 사용
            db.execSQL("update bbs set" +
                    " title = " + "'" + data.title + "'," +
                    " name = " + "'" + data.name + "'," +
                    " contents = " + "'" + data.contents + "'," +
                    " ndate = " + "'" + data.ndate + "'" +
                    " where no = " + data.no
            );
            // 쿼리를 실행 후 결과값을 Cursor 리턴해준다 즉 select문에 사용
            // db.rawQuery("",null);
        }

    }

    private void deleteDB(int position) {
        BbsData data = getData(position);
        if(db != null) {
            // 쿼리를 실행해준다 select 문을 제외한 모든 쿼리에 사용
            db.execSQL("delete from bbs " + "where no = " + data.no);
        }
        datas.remove(position);
    }

    @Override
    public void onFragmentInteraction(int actonFlag, int position) {

        switch (actonFlag) {
            case ACTION_WRITE:
                pager.setCurrentItem(EDIT_FRAGMENT);
                editfragment.update(position);
                break;
            case ACTION_SAVE:
                if(position == EditFragment.WRITE_NEW_DATA) {
                    insertDB();
                } else {
                    updateDB(position);
                }
                listfragment.update();
                pager.setCurrentItem(LIST_FRAGMENT);
                break;
            case ACTION_CANCEL:
                pager.setCurrentItem(LIST_FRAGMENT);
                break;
            case ACTION_MODIFY:
                editfragment.update(position);
                pager.setCurrentItem(EDIT_FRAGMENT);
                break;
            case ACTION_DELETE:
                deleteDB(position);
                listfragment.update();
                pager.setCurrentItem(LIST_FRAGMENT);
        }
    }




    @Override
    public ArrayList<BbsData> getDatas() {
        return datas;
    }

    @Override
    public BbsData getData(int position) {
        return datas.get(position);
    }


    class CustomPagerAdapter extends FragmentStatePagerAdapter {

        public CustomPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment = null;
            // 페이저의 첫번째 화면에는 목록 프래그먼트
            //       두번째 화면에는 상세 프래그먼트
            switch(position) {
                case 0 : fragment = listfragment; break;
                case 1 : fragment = editfragment; break;
            }

            return fragment;
        }

        @Override
        public int getCount() {
            return 2;
        }
    }

    public String getFullpath(String fileName) {
        // 저장할 위치에 파일을 생성해둔다

        return getFilesDir().getAbsolutePath() + File.separator + fileName;

    }

    public void assetToDisk(String fileName) {
        InputStream is = null;
        BufferedInputStream bis = null;

        FileOutputStream fos = null;
        BufferedOutputStream bos = null;

        try {
            // 외부에서 작성된 sqlite db파일 사용하기
            // 1. assets에 담아둔 파일을 internal 혹은 external 공간으로 복사하기위해 읽어온다

            AssetManager manager = getAssets();

            is = manager.open(fileName);
            // assets 에 파일이 없으면 exception 이 발생하여 아래 로직이 실행되지 않는다
            bis = new BufferedInputStream(is);

            // 저장할 위치에 파일을 생성해둔다

            File file = new File(getFullpath(fileName));

            if (!file.exists())

            {
                file.createNewFile();
            }

            // 3. outputStream 을 생성해서 파일내용을 쓴다
            fos = new FileOutputStream(file);

            bos = new BufferedOutputStream(fos);

            // 읽어올 데이터를 담아줄 변수
            int read = -1; // 모두 읽어오면 -1이 리턴된다
            // 한번에 읽을 버퍼의 크기를 지정
            byte buffer[] = new byte[1024];
            // 더이상 읽어올 데이터가 없을때까지 buffer 단위로 읽어서 쓴다
            while ((read = bis.read(buffer, 0, 1024)) != -1) {
                bos.write(buffer, 0, read);
            }
            // 남아 있는 데이터를 buffer에서 써준다
            bos.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try{
                if (bos != null) bos.close();
                if (fos != null) fos.close();
                if (bis != null) bis.close();
                if (is != null) is.close();
            }catch (Exception e){

            }
        }
    }
}
