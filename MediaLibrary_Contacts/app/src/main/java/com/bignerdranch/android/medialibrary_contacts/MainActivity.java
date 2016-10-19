package com.bignerdranch.android.medialibrary_contacts;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Build;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;
    public static ArrayList<RecyclerData> datas = null;

    private final static int REQUEST_CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_card);

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            initData();
        } else {
            checkPermissions();
        }
    }

    @TargetApi(Build.VERSION_CODES.M) // 이버전일때만 실행된다
    private void checkPermissions() {
        if(checkSelfPermission(Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED
                ) {
            // 쓰기 권한이 없으면 로직 처리
            // 중간에 권한 내용에 대한 알림을 처리하는 함수
            //shouldShowRequestPermissionRationale()

            String permissionArray[] =  {Manifest.permission.READ_CONTACTS};

            requestPermissions(permissionArray, REQUEST_CODE); // 권한 획득을위한 호출


        } else {
            // 쓰기 권한이 있으면 로직 처리
            initData();
        }
    }

    public void initData() {
        datas = getPhoneNumbers();
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerCardView);
        RecyclerCardAdapter adapter = new RecyclerCardAdapter(this,datas,R.layout.activity_recycler_card_item);
        mRecyclerView.setAdapter(adapter);


        // 수직 수평 스크롤이 가능
        //RecyclerView.LayoutManager manager = new LinearLayoutManager(this);
        // 그리드 타입
        //RecyclerView.LayoutManager manager = new GridLayoutManager(this, 3);
        // StaggerdGrid 타입  StaggeredGridLayoutManager(3, OrientationHelper.VERTICAL)
        RecyclerView.LayoutManager manager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(manager);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        //super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_CODE:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    initData();
                }
                break;
        }
    }

    public ArrayList<RecyclerData> getPhoneNumbers() {
        ArrayList<RecyclerData> datas = new ArrayList<>();

        Cursor cursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                null, null, null,
                ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " asc");

        String beforeName = "";

        if (cursor != null) {
            while (cursor.moveToNext()) {
                RecyclerData data = new RecyclerData();

                data.name = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                data.tel = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));

                if (!beforeName.equals(data.name)) { // name과 이전 이름과 같지않으면
                    beforeName = data.name;         // beforeName 에 현재 이름 넣어주고
                    datas.add(data);                // 데이터셋에 추가
                }
            }
        }

        cursor.close();

        return datas;
    }


    public ArrayList<RecyclerData> getContacts() {
        ArrayList<RecyclerData>  datas = new ArrayList<>();

        /*
         *           테스트코드
         */
//        for(int i=0;i<100;i++) {
//            RecyclerData data = new RecyclerData();
//            data.name = i + "홍길동";
//            data.tel = "010-1234-1123";
//
//
//            datas.add(data);
//        }
        /*********************************************/


        String projections[] = {
                ContactsContract.Contacts.DISPLAY_NAME,
                ContactsContract.Contacts._ID
        };

        // 조건걸
        //String selection = MediaStore.Audio.Media.TITLE + " != '야생화'";

        //getContentResolver().query(주소, 검색해올컴럼명들, 조건절, 조건절에 매핑되는 값, 정렬)
        Cursor cursor = getContentResolver().query(ContactsContract.Contacts.CONTENT_URI,
                projections,null,null,null);
        /*

          - uri           : content: 스키마 형태로 정해져 있는곳의 데이터를 가져온다
          - projection    : 가져올 컬럼 이름들의 배열 null 을 입력하면 모든 값을 가져온다
          - selection     : 조건절(where)에 해당하는 내용
          - selectionArgs : 조건절이 preparedstatement 형태일때 ? 에 매핑되는 값을 배열
          - sort order    : 정렬 조건

         */

        if (cursor != null) {
            while(cursor.moveToNext()) { // 커서가 다음으로 이동할수 있으면
                RecyclerData data = new RecyclerData();

                int idIndex = cursor.getColumnIndex(ContactsContract.Contacts._ID);
                String contactId = cursor.getString(idIndex); //주소록 ID

                /*
                 *  전화번호는  주소록과 다른데이터셋에 있어서 다시한번 쿼리를함
                 *  조건은 주소록id와 전화번호 데이터셋의 id 가 같은것을 찾아서
                 */
                Cursor phoneCursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                        null,
                        ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = " + contactId, // 조건절로 폰의아이디 = 주소록의아이디
                        null,null);

                if (phoneCursor.moveToFirst()) { // 전화번호를 하나만 가져오기위한 조건걸어줌
                    // 전화번호를 하나만 가져와서 세팅
                    String tel = phoneCursor.getString(phoneCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                    data.tel = tel;
                }

                phoneCursor.close();

                data.name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));

                datas.add(data);
            }
        }

        cursor.close();

        return datas;
    }

}
