package com.bignerdranch.android.threadbasic_rain;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telecom.Call;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

public class MainActivity extends AppCompatActivity {

    public static boolean running = true;
    public static int deviceWidth = 0;
    public static int deviceHeight = 0;

    FrameLayout ground;
    Button btnStart;
    Button btnStop;

    CustomView customView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        deviceWidth = displayMetrics.widthPixels;   // 화면의 가로 픽셀개수
        deviceHeight = displayMetrics.heightPixels; // 세로 픽셀개수

        ground = (FrameLayout) findViewById(R.id.ground);
        btnStart = (Button) findViewById(R.id.button_start);
        btnStop = (Button) findViewById(R.id.button_stop);

        customView = new CustomView(this);
        ground.addView(customView);



        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                running = true;
                new MakeDrop(customView).start();
            }
        });

        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                running = false;
            }
        });

    }

    // 빗방울을 만들고 화면을 그려주는 Thread를 동작시키는 서브 Thread
    class MakeDrop extends Thread {

        CustomView customView;

        public MakeDrop(CustomView customView) {
            this.customView = customView;

        }

        public void run() {

            CallDraw callDraw = new CallDraw(customView, 10);
            new Thread(callDraw).start();

            while (running) {
                RainDrop rain = new RainDrop(customView);

                new Thread(rain).start();

                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }
    }



    class CustomView extends View {

        List<RainDrop> rainDrops = Collections.synchronizedList(new ArrayList<RainDrop>());
        
        Paint paint = new Paint();

        public CustomView(Context context) {
            super(context);
            paint.setColor(Color.BLUE);
        }

        public void add(RainDrop rd) {
            rainDrops.add(rd);
        }

        public void remove(RainDrop rd) {
            rainDrops.remove(rd);
        }
        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);

            synchronized (rainDrops) {
                for (RainDrop rainDrop : rainDrops) {
                    canvas.drawCircle(rainDrop.x, rainDrop.y, rainDrop.size, paint);
                }
            }

        }

    }

    // 빗방울 1개
    class RainDrop implements Runnable {

        // 자신의 좌표값
        int size_limit;
        int speed_limit;
        int x;
        int y;
        int size;
        int speed;
        CustomView customView;

        public RainDrop(CustomView customView) {

            this.customView = customView;
            customView.add(this);
            Random random = new Random();

            x = random.nextInt(deviceWidth);
            y = 0;

            size_limit = deviceWidth / 20; // 빗방울의 최대크기가 화면사이즈의 1/20보다 작게 만든다.
            size = random.nextInt(size_limit) + 1;
            speed_limit = deviceHeight / 100;
            speed = random.nextInt(speed_limit) + 1;
        }

        @Override
        public void run() {
            while (y <= deviceHeight) { // 화면 밖으로 벗어나면 while문을 빠져나가 Thread는 종료된다
                if (running) {
                    y = y + speed;
                }
                try {
                    // 0.1초에 한번 이동
                    Thread.sleep(10); // y축으로 이동후 그려지는 간격을 조절한다
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            customView.remove(this);
        }
    }

    // 화면을 그려주는 thread
    class CallDraw implements Runnable {

        CustomView cv;
        int interval;

        public CallDraw(CustomView cv, int interval) {
            this.cv = cv;
            this.interval = interval;
        }


        @Override
        public void run() {
            // interval에 입력된 값만큼 쉰후에 화면을 반복해서 그려준다.
            while (running) {
                cv.postInvalidate();

                try {
                    Thread.sleep(interval);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}
