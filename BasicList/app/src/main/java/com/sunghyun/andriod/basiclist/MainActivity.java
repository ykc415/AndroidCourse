package com.sunghyun.andriod.basiclist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{


    Button btn1;
    Button btn2;
    Button btn3;
    Button btn4;
    Button btn5;
    Button btn6;
    Button btn7;
    Button btn8;
    Button btn9;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn1 = (Button) findViewById(R.id.button);
        btn2 = (Button) findViewById(R.id.button2);
        btn3 = (Button) findViewById(R.id.button3);
        btn4 = (Button) findViewById(R.id.button4);
        btn5 = (Button) findViewById(R.id.button5);
        btn6 = (Button) findViewById(R.id.button6);
        btn7 = (Button) findViewById(R.id.button7);
        btn8 = (Button) findViewById(R.id.button8);
        btn9 = (Button) findViewById(R.id.button9);

        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);
        btn8.setOnClickListener(this);
        btn9.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        Intent intent = null;
        switch(view.getId()) {
            case R.id.button:
                intent = new Intent(this, BasicList1Activity.class);
                break;
            case R.id.button2:
                intent = new Intent(this, BasicList2Activity.class);
                break;
            case R.id.button3:
                intent = new Intent(this, CustomListActivity.class);
                break;
            case R.id.button4:
                intent = new Intent(this, BasicGridActivity.class);
                break;
            case R.id.button5:
                intent = new Intent(this, CustomGridActivity.class);
                break;
            case R.id.button6:
                intent = new Intent(this, ExpandableActivity.class);
                break;
            case R.id.button7:
                intent = new Intent(this, RecyclerActivity.class);
                break;
            case R.id.button8:
                intent = new Intent(this, RecyclerAnimationActivity.class);
                break;
            case R.id.button9:
                intent = new Intent(this, RecyclerCardActivity.class);
                break;


        }
        startActivity(intent);
    }
}
