package com.kodonho.android.sqlitebasic_bbs;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class EditFragment extends Fragment {

    private static final int BBS_INSERT = -1;
    private static final int REQ_CODE_IMAGE = 99;


    // 메인액티비티와 통신하는 리스너
    OnFragmentListener listener;

    Button cancel;
    Button save;
    Button image;

    int bbsno = -1;

    EditText title;
    EditText name;
    EditText contents;
    ImageView imageView;


    public EditFragment() {
        // Required empty public constructor
    }

    // Database 에서 bbsno 에 해당하는 레코드를 가져와서 화면에 뿌려준다
    public void setData(int bbsno){
        BbsData data = DataUtil.select(getContext(), bbsno);
        Log.i("setData","bbsno="+bbsno);
        Log.i("setData","title="+data.title);
        Log.i("setData","name="+data.name);
        Log.i("setData","contents="+data.contents);
        title.setText(data.title);
        name.setText(data.name);
        contents.setText(data.contents);
        this.bbsno = bbsno;
    }

    // 캔슬하거나 저장후에 호출하여 텍스트필드값을 초기화해준다
    public void resetData(){
        title.setText("");
        name.setText("");
        contents.setText("");
        this.bbsno = -1;
    }

    public static EditFragment newInstance(String param1, String param2) {
        EditFragment fragment = new EditFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit, container, false);
        cancel = (Button) view.findViewById(R.id.btnCancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.action(MainActivity.ACTION_CANCEL);
                resetData();
            }
        });
        save = (Button) view.findViewById(R.id.btnSave);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BbsData data = new BbsData();
                data.no = bbsno;
                data.title = title.getText().toString();
                data.name = name.getText().toString();
                data.contents = contents.getText().toString();
                if(bbsno == BBS_INSERT) {
                    DataUtil.insert(getContext(), data);
                }else{
                    DataUtil.update(getContext(), data);
                }
                resetData();
                listener.action(MainActivity.ACTION_GOLIST_WITH_REFRESH);
            }
        });
        image = (Button) view.findViewById(R.id.img_button);
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK,
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI); // 이미지를 호출하는 action Intent
                startActivityForResult(intent, REQ_CODE_IMAGE);       // 결과값을 넘겨받기 위해 호출
            }
        });


        imageView = (ImageView) view.findViewById(R.id.edit_imageView);
        name = (EditText) view.findViewById(R.id.etName);
        title = (EditText) view.findViewById(R.id.etTitle);
        contents = (EditText) view.findViewById(R.id.etContents);

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == REQ_CODE_IMAGE && data != null) {
            Uri mediaImage = data.getData();                       // 갤러리 Uri

            String selections[] = {MediaStore.Images.Media.DATA}; // 실제 이미지 패스 데이터
            Cursor cursor = getContext().getContentResolver().query(mediaImage, selections,null,null,null);

            if(cursor.moveToNext()) {
                String imagePath = cursor.getString(0);
                name.setText(imagePath);

                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inJustDecodeBounds = true; // 영역내의 크기로 그려주기
                // 사이즈 지정 옵션
                // 스케일 지정 옵션
                options.inSampleSize = 4; // 이미지 사이즈를 1/4로 줄인다
                Bitmap image = BitmapFactory.decodeFile(imagePath);
               // imageView.setImageBitmap(BitmapFactory.decodeFile(imagePath));
            }
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        // 메인 액티비티가 OnFragmentListener를 구현했는지 확인
        if(context instanceof OnFragmentListener){
            listener = (OnFragmentListener) context;
        }else{ // 구현하지 않았으면 MainActivity와 통신할 방법이 없으므로 앱을 죽인다
            throw  new RuntimeException();
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
