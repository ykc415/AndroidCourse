package com.sunghyun.android.firebase_database;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class BbsActivity extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference rootRef;
    DatabaseReference postRef;

    EditText etTitle;
    EditText etName;
    EditText etcontents;

    Button post;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bbs);

        database = FirebaseDatabase.getInstance();
        postRef = database.getReference("posts");
        rootRef = database.getReference();

        etTitle = (EditText) findViewById(R.id.editText_title);
        etName = (EditText) findViewById(R.id.editText_name);
        etcontents = (EditText) findViewById(R.id.editText_contents);

        post = (Button) findViewById(R.id.button_post);
        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = etTitle.getText().toString().trim();
                String name = etName.getText().toString().trim();
                String body = etcontents.getText().toString().trim();

                writeNewPost("pica415", name, title, body);
            }
        });

    }


    private void writeNewPost(String userId, String username, String title, String body) {

        // Create new post at /user-posts/$userid/$postid and at
        // /posts/$postid simultaneously
        String key = postRef.child("posts").push().getKey();
        Post post = new Post(userId, username, title, body);
        Map<String, Object> postValues = post.toMap();

        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put("/posts/" + key, postValues);
        childUpdates.put("/user-posts/" + userId + "/" + key, postValues);

        rootRef.updateChildren(childUpdates);
    }
}
