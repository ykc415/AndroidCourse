package com.kodonho.android.sensor_basic01;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/*
    - 센서 동작 기본 흐름
    1. SensorManger 생성
    2. Sensor 객체 생성 (사용할 Sersor Type 선택)
    3. Sensor Listener 작성
    4. Listener 등록 및 Sensor 값 받기
    5. Listener 해제

    - 동작속도
    FASTEST, GAME, UI, NORMAL

    - 정확도
    HIGH, MEDIUM, LOW

 */

public class MainActivity extends AppCompatActivity implements SensorEventListener{

    SensorManager manager;
    Sensor acc;
    Sensor light;
    Sensor step;

    TextView tvAcc;
    TextView tvLight;
    TextView tvStep;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvAcc = (TextView) findViewById(R.id.accValue);
        tvLight = (TextView) findViewById(R.id.lightValue);
        tvStep = (TextView) findViewById(R.id.tvStep);

        //1
        manager = (SensorManager) getSystemService(SENSOR_SERVICE);
        //2
        acc = manager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        light = manager.getDefaultSensor(Sensor.TYPE_LIGHT);
        step = manager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        synchronized (this){
            float x = 0;
            float y = 0;
            float z = 0;
            // 이벤트를 발생시킨 센서타입을 체크한다
            switch (event.sensor.getType()){
                case Sensor.TYPE_ACCELEROMETER:
                    x = event.values[0];
                    y = event.values[1];
                    z = event.values[2];
                    tvAcc.setText("x="+x+", y="+y+", z="+z);
                    break;
                case Sensor.TYPE_LIGHT:
                    x = event.values[0];
                    tvLight.setText("temp.="+x);
                    break;
                case Sensor.TYPE_STEP_COUNTER:
                    x = event.values[0];
                    tvStep.setText("Step="+x);
                    break;
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        manager.registerListener(this, acc, SensorManager.SENSOR_DELAY_NORMAL);
        manager.registerListener(this, light, SensorManager.SENSOR_DELAY_NORMAL);
        manager.registerListener(this, step, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        manager.unregisterListener(this);
    }
}
