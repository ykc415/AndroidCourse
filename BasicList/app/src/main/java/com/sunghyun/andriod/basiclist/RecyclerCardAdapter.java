package com.sunghyun.andriod.basiclist;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by YKC on 2016. 9. 28..
 */
public class RecyclerCardAdapter extends RecyclerView.Adapter<RecyclerCardAdapter.ViewHolder>{

    ArrayList<RecyclerData> datas;
    int itemLayout;
    Context context;
    RecyclerData inputdata;

    public RecyclerCardAdapter(Context context, ArrayList<RecyclerData> datas, int itemLayout) {
        this.context = context;
        this.datas = datas;
        this.itemLayout = itemLayout;
    }

    // View 를 만들어서 홀더에 저장하는역할
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(itemLayout, parent ,false);

        return new ViewHolder(view);
    }

    // 일반 ListAdapter의 getView 를 대체하는 함수
    @Override
    public void onBindViewHolder(ViewHolder holder,final int position) {
        RecyclerData data = datas.get(position);
        inputdata = data;
        holder.image.setImageResource(data.image);

        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context,"이미지가 클릭됨",Toast.LENGTH_SHORT).show();
            }
        });

        holder.title.setText(data.title);
//        holder.title.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(context,DetailActivity.class);
//                 context.startActivity(intent);
//            }
//        });


        holder.itemView.setTag(data);


        setanimation(holder.image, position);

    }


    @Override
    public int getItemCount() {
        return datas.size();
    }

    //데이터를 재사용해주는 객체
    public static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView image;
        TextView title;
        CardView card;

        public ViewHolder(View itemView) {
            super(itemView);


            image = (ImageView) itemView.findViewById(R.id.image);
            title = (TextView) itemView.findViewById(R.id.title);
            card = (CardView) itemView.findViewById(R.id.carditem);
        }
    }

    int lastPosition = -1;

    public void setanimation(View view, int position) {

        if (position > lastPosition) {
            Animation animation = AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left);
            view.startAnimation(animation);
            lastPosition = position;
        }
    }

}
