package com.kodonho.android.firebase_database01;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class DetailActivity extends AppCompatActivity {

    int position = -1;
    Branch branch;

    RecyclerView listView;
    RecyclerCardAdapter<MENU> adapter;

    ImageView imageView;
    TextView storeTitle;
    TextView branchTitle;
    TextView fee;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        position = intent.getExtras().getInt("position");
        branch = MainActivity.branches.get(position);

        imageView = (ImageView) findViewById(R.id.imageView);
        storeTitle = (TextView) findViewById(R.id.store);
        branchTitle = (TextView) findViewById(R.id.branch);
        fee = (TextView) findViewById(R.id.fee);

        Glide.with(this).load(branch.getLOGO()).into(imageView);
        storeTitle.setText(branch.getNAME());
        branchTitle.setText(branch.getBRANCH());
        fee.setText(branch.getDELIVERY_FEE()+"");

        listView = (RecyclerView) findViewById(R.id.listView);
        adapter = new RecyclerCardAdapter<>(branch.getMENU(),R.layout.main_list_item,this);
        listView.setAdapter(adapter);

        RecyclerView.LayoutManager manager = new LinearLayoutManager(this);
        listView.setLayoutManager(manager);

    }
}
