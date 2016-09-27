package com.sunghyun.andriod.basicwidget;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.Date;

public class MainActivity extends AppCompatActivity {

    // 결과값이 출력된ㄴ 텍스트뷰
    TextView tv;
    // 라디오그룹
    RadioGroup rg;
    // 체크박스
    CheckBox cb1, cb2, cb3;
    // 스위치
    Switch sch;
    // 토글버튼
    ToggleButton tog;
    // 프로그래스바
    ProgressBar pb;
    // Seek바
    SeekBar sb;
    TextView sb_tv;
    // Rating Bar
    RatingBar rb;
    TextView rb_tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = (TextView) findViewById(R.id.textView2);

        rg = (RadioGroup) findViewById(R.id.radioGroup);

        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                //int checked = rg.getCheckedRadioButtonId(); // 현재 체크된 라디오버튼 아이디를 가져온다
                Intent intent = null;
                switch (checkedId) {

                    case R.id.rdApple:
                        tv.setText("Apple이 선택됨");
                        intent = new Intent(MainActivity.this, DateActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.rdOrange:
                        tv.setText("Orange가 선택됨");
                        intent = new Intent(MainActivity.this, TextActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.rdBanana:
                        tv.setText("Banana가 선택됨");
                        intent = new Intent(MainActivity.this, TabActivity.class);
                        startActivity(intent);
                        break;
                }
            }
        });

        cb1 = (CheckBox) findViewById(R.id.cbChicken);
        cb2 = (CheckBox) findViewById(R.id.cbDog);
        cb3 = (CheckBox) findViewById(R.id.cbPig);

        cb1.setOnCheckedChangeListener(checkedChangeListener);
        cb2.setOnCheckedChangeListener(checkedChangeListener);
        cb3.setOnCheckedChangeListener(checkedChangeListener);

        sch = (Switch) findViewById(R.id.switch1);

        sch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    tv.setText("스위치 ON");
                    pb.setVisibility(View.VISIBLE);
                } else {
                    tv.setText("스위치 OFF");
                    pb.setVisibility(View.INVISIBLE);
                }

            }
        });

        tog = (ToggleButton) findViewById(R.id.toggleButton);

        tog.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    Intent intent = new Intent(MainActivity.this, SpinnerActivity.class);
                    startActivity(intent);
                    Toast.makeText(MainActivity.this,"토글 ON",Toast.LENGTH_LONG).show();
                } else  {
                    Toast.makeText(MainActivity.this,"토글 OFF",Toast.LENGTH_LONG).show();
                }
            }
        });

        pb = (ProgressBar) findViewById(R.id.progressBar);

        pb.setVisibility(View.INVISIBLE);

        sb = (SeekBar) findViewById(R.id.seekBar);
        sb_tv = (TextView) findViewById(R.id.textView9) ;

        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                sb_tv.setText(progress+" %");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                Toast.makeText(MainActivity.this, seekBar.getProgress()+"위치에서 터치가 시작됨",
                        Toast.LENGTH_LONG).show();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Toast.makeText(MainActivity.this, seekBar.getProgress()+"위치에서 터치가 종료됨",
                        Toast.LENGTH_LONG).show();
            }
        });


        rb = (RatingBar) findViewById(R.id.ratingBar);
        rb_tv = (TextView) findViewById(R.id.textView11);

        rb.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                rb_tv.setText(rating+"/5");
            }
        });
    }


    //컴파운드 계열(체크박스, 토글, 스위치... 등 버튼에서 사용되는 체크변화를 감지하는 리스너
    CompoundButton.OnCheckedChangeListener checkedChangeListener =
            new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                    StringBuilder sb = new StringBuilder();

                    if(cb1.isChecked()) {
                        sb.append("Chicken ");
                    }
                    if(cb2.isChecked()) {
                        sb.append("Dog ");
                    }
                    if(cb3.isChecked()) {
                        sb.append("Pig ");
                    }

                    tv.setText(sb.toString());
                }

            };

}
