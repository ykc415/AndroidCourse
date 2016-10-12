package com.bignerdranch.android.sqlitebasic_bbs;

import android.content.Context;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.security.Timestamp;
import java.util.ArrayList;

/**
 * Created by YKC on 2016. 10. 11..  연
 */

public class DataUtil {


    private static ArrayList<BbsData> datas = new ArrayList<>();
    private SQLiteDatabase db;


    public DataUtil() {

    }

    private static SQLiteDatabase openDB(Context context) {
        // 데이터베이스를 연결하는 Api
        return SQLiteDatabase.openDatabase(getFullpath(context,"sqlite.db"),null,0);
        // 0: 쓰기가능 1: read only
    }

    private static void insertDB(Context context) {
        SQLiteDatabase db = openDB(context);
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

    private  static void selectDB(Context context) {
        SQLiteDatabase db = openDB(context);
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

    private static void updateDB(Context context,int position) {
        SQLiteDatabase db = openDB(context);
        BbsData data = datas.get(position);
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

    private static void deleteDB(Context context, int position) {
        SQLiteDatabase db = openDB(context);
        BbsData data = datas.get(position);
        if(db != null) {
            // 쿼리를 실행해준다 select 문을 제외한 모든 쿼리에 사용
            db.execSQL("delete from bbs " + "where no = " + data.no);
        }
        datas.remove(position);
    }

    public static String getFullpath(Context context, String fileName) {
        // 저장할 위치에 파일을 생성해둔다
        return context.getFilesDir().getAbsolutePath() + File.separator + fileName;

    }

    public static void assetToDisk(Context context, String fileName) {
        InputStream is = null;
        BufferedInputStream bis = null;

        FileOutputStream fos = null;
        BufferedOutputStream bos = null;

        try {
            // 외부에서 작성된 sqlite db파일 사용하기
            // 1. assets에 담아둔 파일을 internal 혹은 external 공간으로 복사하기위해 읽어온다

            AssetManager manager = context.getAssets();

            is = manager.open(fileName);
            // assets 에 파일이 없으면 exception 이 발생하여 아래 로직이 실행되지 않는다
            bis = new BufferedInputStream(is);

            // 저장할 위치에 파일을 생성해둔다

            File file = new File(getFullpath(context,fileName));

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
