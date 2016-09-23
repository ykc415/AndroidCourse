package com.sunghyun.andriod.activitybasic02;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;



import org.apache.commons.jexl2.Expression;
import org.apache.commons.jexl2.JexlEngine;



public class SubActivity extends AppCompatActivity {

    EditText et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        et = (EditText) findViewById(R.id.editText2);
        Button button = (Button) findViewById(R.id.button2);

        Intent intent = getIntent();

        Bundle bundle = intent.getExtras();

        String str = bundle.getString("key1");
        et.setText(eval(str));


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 1. 에딧 택스트에서 값을 가져온다
                String result = et.getText().toString();
                // 2. 인텐트를 생성하고 돌려줄값을 세팅한다
                Intent intent = getIntent(); // getIntent()로 전달받은 인텐트를 가져와서 사용할수있지마나 설계상 new로 새로 만드는게 낫다
                intent.putExtra("return1", result);
                intent.putExtra("resutn2", "결과값");
                // 3. setResult에 결과코드와 데이터를 넘겨준다
                setResult(1,intent);
                // 현재 액티비티 종료
                finish();
            }
        });
    }





    // 문자열을 수식으로 변경한 후 결과값을 리턴하는 함수
    public String eval(String str){
        String result = "";
        try {
            // 1. jexl 엔진 생성
            JexlEngine jexl = new JexlEngine();
            // 2. 계산식이 담긴 문자열을 jexl 에 담아준다
            Expression e = jexl.createExpression(str);
            // 3. evaluate 함수를 통해 연산을 하고 "" 를 붙혀서 문자열로 변경한다
            result = e.evaluate(null) + "";
        }catch(Exception e){
            e.printStackTrace();
        }
        return result;
    }
}
