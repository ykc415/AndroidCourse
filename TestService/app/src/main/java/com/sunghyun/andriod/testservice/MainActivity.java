package com.sunghyun.andriod.testservice;

import android.animation.ObjectAnimator;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    static final int REQUEST_CODE = 1;   // 요청한값 받기위한 상수
    static final String KEYCODE = "com.sunghyun.andriod.testservice"; //이벤트에서 엑티비티가 넘겨준 팬딩 열기위한 상수

    Button button;
    Button button1;
    public Integer angle = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.button);
        button1 = (Button) findViewById(R.id.button2);

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == REQUEST_CODE) { // 요청한 결과가 넘어온건지 확인
            Bundle bundle = data.getExtras();
            angle = bundle.getInt(MyIntentService.RESULT);  //이벤트에서 받아온 값을 설정
            Log.i("Main", "angle" + angle);
            startAnimation(); // 애니메이션 ㄱㄱ
        }
    }

    public void startIntentService(View v) {

        PendingIntent pending = createPendingResult(REQUEST_CODE,new Intent(),0); //팬딩인텐트를 되돌려받을 상수, 센더에서 사용될 인텐트, 플래그

        Intent intent = new Intent(this,MyIntentService.class);//서비스명 class
        intent.putExtra(KEYCODE,pending); // 팬딩인텐트를 intent에 넣어줌
        startService(intent);

    }

    public void startAnimation() {
        ObjectAnimator ani = ObjectAnimator.ofFloat(button1, "rotation", angle);
        ani.start();
    }
}
