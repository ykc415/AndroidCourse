package com.sunghyun.android.firebase_auth;

import android.os.Bundle;
import android.support.annotation.NonNull;
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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    FirebaseDatabase database;
    DatabaseReference userRef;
    Button button_Signin;
    Button button_Signup;
    Button button_Signout;
    EditText etemail;
    EditText etpassword;
    ListView listView;

    ArrayList<Map<String,User>> datas = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button_Signin = (Button) findViewById(R.id.button_signin);
        button_Signup = (Button) findViewById(R.id.button_signup);
        button_Signout = (Button) findViewById(R.id.button_signout);
        etemail = (EditText) findViewById(R.id.editText_email);
        etpassword = (EditText) findViewById(R.id.editText_password);



        // 데이터베이스 커넥션
        database = FirebaseDatabase.getInstance();

        // 1. 인증객체 가져오기
        mAuth = FirebaseAuth.getInstance();

        // 2. 리스너 설정
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
                // ...
            }
        };

        // 4. 신규계정 생성
        button_Signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = etemail.getText().toString().trim();
                String pw = etpassword.getText().toString().trim();
                if(!"".equals(email) && !"".equals(pw)) {
                    addUser(email, pw);
                } else {
                    Toast.makeText(MainActivity.this, "Email 과 Password 를 입력하셔야 합니다", Toast.LENGTH_SHORT).show();
                }
            }
        });

        button_Signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = etemail.getText().toString().trim();
                String pw = etpassword.getText().toString().trim();
                if(!"".equals(email) && !"".equals(pw)) {
                    signIn(email, pw);
                } else {
                    Toast.makeText(MainActivity.this, "Email 과 Password 를 입력하셔야 합니다", Toast.LENGTH_SHORT).show();
                }
            }
        });

        button_Signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
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

    public void signIn(String email, String pw) {
        mAuth.signInWithEmailAndPassword(email, pw)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!task.isSuccessful()) {
                            Log.w(TAG, "signInWithEmail:failed", task.getException());
                            Toast.makeText(MainActivity.this, "Sign In에 실패하였습니다.",
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(MainActivity.this, "Sign In에 성공하였습니다.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        Toast.makeText(MainActivity.this, "로그인성공", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(MainActivity.this, "로그인실패", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public void addUser(String email, String pw) {
        mAuth.createUserWithEmailAndPassword(email, pw)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!task.isSuccessful()) {
                            Log.w(TAG, "signInWithEmail:failed", task.getException());
                            Toast.makeText(MainActivity.this, "사용자 등록에 실패하였습니다.",
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(MainActivity.this, "사용자 등록에 성공하였습니다.",
                                    Toast.LENGTH_SHORT).show();
                        }

                    }
                })
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        Toast.makeText(MainActivity.this, "가입 성공",
                                Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(MainActivity.this, "가입 실패",
                                Toast.LENGTH_SHORT).show();
                    }
                });
    }

    // 3. 리스너 해제 및 재등록 처리

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    class ListAdapter extends BaseAdapter {

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
