package com.bignerdranch.android.loadinglibrary;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button) findViewById(R.id.button);
        final ImageView imageView = (ImageView) findViewById(R.id.imageView);
        final ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String url = "http://www.zamong.co.kr/wp-content/uploads/2015/11/%EA%B3%A0%EB%A6%B4%EB%9D%BCi11319484924.jpg";
                progressBar.setVisibility(View.VISIBLE);
                Picasso.with(MainActivity.this).load(url).into(imageView, new Callback() {
                    @Override
                    public void onSuccess() {
                        progressBar.setVisibility(View.GONE);
                    }

                    @Override
                    public void onError() {

                    }
                });

//                Glide.with(MainActivity.this)
//                        .load(url)
//                        .listener(new RequestListener<String,GlideDrawable>() {
//
//                            @Override
//                            public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
//                                return false;
//                            }
//
//                            @Override
//                            public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
//                                progressBar.setVisibility(View.GONE);
//                                return false;
//                            }
//                        })
//                        .into(imageView);
            }
        });
    }
}
