package com.sunghyun.android.firebase_database;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference rootRef;
    DatabaseReference userRef;

    EditText etUid;
    EditText etName;
    EditText etEmail;
    Button btnAdd;
    Button btnOpen;

    ListView listView;
    ArrayList<Map<String,User>> datas = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 데이터베이스 커넥션
        database = FirebaseDatabase.getInstance();


        btnOpen = (Button) findViewById(R.id.btnOpen);
        etUid = (EditText) findViewById(R.id.etUid);
        etName = (EditText) findViewById(R.id.etName);
        etEmail = (EditText) findViewById(R.id.etEmail);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uid = etUid.getText().toString().trim();
                String name = etName.getText().toString().trim();
                String email = etEmail.getText().toString().trim();

                if(!"".equals(uid) && !"".equals(name) && !"".equals(email)){
                    writeNewUser(uid,name,email);
                    etUid.setText("");
                    etName.setText("");
                    etEmail.setText("");
                }else{
                    Toast.makeText(MainActivity.this,"아이디, 이름, 이메일을 입력하세요",Toast.LENGTH_LONG).show();
                }
            }
        });

        btnOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, BbsActivity.class);
                startActivity(i);
            }
        });

        listView = (ListView) findViewById(R.id.listView);
        ListAdapter adapter = new ListAdapter();
        listView.setAdapter(adapter);



        // 참조포인트
        userRef = database.getReference("users");
        userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot users) {
                Log.e("FireBase","snapshot="+users.getValue());

                datas = new ArrayList<>();
                for(DataSnapshot userData : users.getChildren()){
                    try {
                        Map<String, User> data = new HashMap<>();
                        String userId = userData.getKey();
                        User user = userData.getValue(User.class);
                        data.put(userId, user);
                        datas.add(data);
                    }catch(Exception e){
                        // 데이터 구조가 달라서 매핑이 안될경우 예외처리
                        e.printStackTrace();
                    }
                }
                listView.deferNotifyDataSetChanged();
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void writeNewUser(String userId, String name, String email) {
        User user = new User(name, email);
        userRef.child(userId).setValue(user);
        /*
           root - users - michael - name : 누구
                                  - email : 어디
                        - javafa  - name : 아무개
                                  - email : 구글
                        - kimtae
         */
    }

    class ListAdapter extends BaseAdapter{

        LayoutInflater inflater;

        public ListAdapter(){
            inflater = getLayoutInflater();
        }

        @Override
        public int getCount() {
            return datas.size();
        }

        @Override
        public Object getItem(int position) {
            return datas.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView == null)
                convertView = inflater.inflate(R.layout.listview_item,null);

            TextView tvName = (TextView)convertView.findViewById(R.id.tvName);
            TextView tvUid = (TextView)convertView.findViewById(R.id.tvUid);
            TextView tvEmail = (TextView)convertView.findViewById(R.id.tvEmail);

            Map<String,User> data = datas.get(position);
            String uid = data.keySet().iterator().next();
            User user = data.get(uid);

            tvUid.setText(uid);
            tvName.setText(user.username);
            tvEmail.setText(user.email);

            return convertView;
        }
    }
}