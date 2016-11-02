package com.kodonho.android.firebase_database01;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static ArrayList<Branch> branches = new ArrayList<>();

    FirebaseDatabase database;
    DatabaseReference chickenRef;
    RecyclerView listView;
    RecyclerCardAdapter<Branch> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        database = FirebaseDatabase.getInstance();
        chickenRef = database.getReference("CHICKENSTORE");

        listView = (RecyclerView) findViewById(R.id.listView);
        adapter = new RecyclerCardAdapter<>(branches,R.layout.main_list_item,this);
        listView.setAdapter(adapter);

        RecyclerView.LayoutManager manager = new LinearLayoutManager(this);
        listView.setLayoutManager(manager);

        chickenRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                branches.clear();
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    Branch branch = snapshot.getValue(Branch.class);
                    branches.add(branch);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
