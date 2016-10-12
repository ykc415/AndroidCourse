package com.bignerdranch.android.sqlitebasic_sqlitedatabase;

import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    TextView result;
    Button openDatabase;
    Button insert;
    Button select;
    Button update;
    Button delete;


    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        openDatabase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 데이터베이스를 연결하는 Api
                db = SQLiteDatabase.openDatabase(
                        getFullpath("sqlite.db"),null,0);
                // 0: 쓰기가능 1: read only
            }
        });

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(db != null) {
                    // 쿼리를 실행해준다 select 문을 제외한 모든 쿼리에 사용
                    db.execSQL("insert into bbs2(no,title,user_name) values(1,'제목','홍길동')");
                    // 쿼리를 실행 후 결과값을 Cursor 리턴해준다 즉 select문에 사용
                    // db.rawQuery("",null);
                }
            }
        });

        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(db != null) {
                    Cursor cursor = db.rawQuery("select * from bbs2", null);
                    while(cursor.moveToNext()) {
                        int idIdx = cursor.getColumnIndex("no");
                        String no = cursor.getString(idIdx);
                        idIdx = cursor.getColumnIndex("user_name");
                        String name = cursor.getString(idIdx);
                        idIdx = cursor.getColumnIndex("title");
                        String title = cursor.getString(idIdx);
                        String temp = result.getText().toString();
                        //result.invalidate();
                        result.setText(temp +"\n no="+no+", name="+name+", title="+title);
                    }
                }
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(db != null) {
                    db.execSQL("delete from bbs2 where no = 1");
                }
            }
        });

    }

    private  void init() {
        File file = new File(getFullpath("sqlite.db"));
        if(!file.exists())
            assetToDisk("sqlite.db");

        result = (TextView) findViewById(R.id.textView);
        openDatabase = (Button) findViewById(R.id.buttonOpen);
        insert = (Button) findViewById(R.id.buttonInsert);
        select = (Button) findViewById(R.id.buttonSelect);
        update = (Button) findViewById(R.id.buttonUpdate);
        delete = (Button) findViewById(R.id.buttonDelete);

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
