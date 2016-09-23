package com.sunghyun.andriod.activitybasic02;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    Button button;
    EditText et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.button);
        et = (EditText) findViewById(R.id.editText);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SubActivity.class);
                intent.putExtra("key1", et.getText().toString());
                intent.putExtra("key2", "박성현");
                intent.putExtra("key3", "ㅇㅇㅇ");
                startActivityForResult(intent, 0);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Send 버튼이 호출했던거
        if(requestCode == 0) {
            if(resultCode == 1) {
                Bundle bundle = data.getExtras();
                String result = bundle.getString("return1");
                et.setText(result);
            }
        }



    }




}
