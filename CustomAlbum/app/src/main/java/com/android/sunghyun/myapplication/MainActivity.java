package com.android.sunghyun.myapplication;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    RecyclerView mRecyclerView;
    List<Uri> datas = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        datas = getImagesFromGallery();

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, datas);
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 3));

    }

    // ContentResolver 로 모든 이미지를 한번에 가져오기  참조 : http://shygiants.github.io/android/2016/01/13/contentresolver.html

    List<Uri> getImagesFromGallery() {
        // DATA는 이미지 파일의 스트림 데이터 경로를 나타낸다.
        String[] projection = { MediaStore.Images.Media.DATA};

        Cursor imageCursor = getContentResolver().query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI, // 이미지 컨텐트 테이블
                projection, // DATA를 출력
                null, // 모든 개체 출력
                null,
                null ); // 정렬 안함

        ArrayList<Uri> result = new ArrayList<>(imageCursor.getCount());
        int dataColumnIndex = imageCursor.getColumnIndex(projection[0]);

        if (imageCursor == null) {

        } else if (imageCursor.moveToFirst()) {
            do {
                String filePath = imageCursor.getString(dataColumnIndex);
                Uri imageUri = Uri.parse(filePath);
                result.add(imageUri);
            } while(imageCursor.moveToNext());
        } else {

        }

        imageCursor.close();

        return result;

    }


}


class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    Context mContext;
    List<Uri> mDatas;


    public RecyclerViewAdapter(Context context, List<Uri> datas) {
        mContext = context;
        mDatas = datas;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.recyclerview_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Log.e("URI", mDatas.get(position)+"");

        Uri uri = mDatas.get(position);
        
        Glide.with(mContext).load(new File(uri.getPath())).into(holder.mImageView);
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView mImageView;

        public ViewHolder(View itemView) {
            super(itemView);

            mImageView = (ImageView) itemView.findViewById(R.id.imageView);
        }
    }
}