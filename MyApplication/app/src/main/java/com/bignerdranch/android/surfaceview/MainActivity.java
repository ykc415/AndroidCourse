package com.bignerdranch.android.surfaceview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class MainActivity extends AppCompatActivity {

    int deviceWidth = 0;
    int deviceHeight = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new CustomSurfaceView(this));

        DisplayMetrics metrics = getResources().getDisplayMetrics();
        deviceHeight = metrics.heightPixels;
        deviceWidth = metrics.widthPixels;
    }

    public class CustomSurfaceView extends SurfaceView implements SurfaceHolder.Callback{

        private SurfaceThread thread;


        public CustomSurfaceView(Context context) {
            super(context);
            getHolder().addCallback(this);
            thread = new SurfaceThread(getHolder()); //

        }

        public CustomSurfaceView(Context context, AttributeSet attrs) {
            super(context, attrs);
        }

        public CustomSurfaceView(Context context, AttributeSet attrs, int defStyleAttr) {
            super(context, attrs, defStyleAttr);
        }

        // 뷰가 화면에 보여지는 시점
        @Override
        public void surfaceCreated(SurfaceHolder holder) {
            thread.start();

        }

        // 뷰에 변경사항 (사이즈 변경 등.)
        @Override
        public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

        }

        // 화면에서 뷰가 보이지 않는 시점
        @Override
        public void surfaceDestroyed(SurfaceHolder holder) {
           boolean retry = true;
           while (retry) {
               try {
                   thread.join();
                   retry = false;
               } catch (Exception e) {
                   e.printStackTrace();
               }
           }
        }
    }

    // 무한반복하면서 위에서 정의한 surface 뷰에 그림을 그려주는 역할을 한다.
    public class SurfaceThread extends Thread {

        private boolean running = true;
        private SurfaceHolder surfaceHolder;
        Paint paint = new Paint();
        int x = 0;
        int y = 0;



        public SurfaceThread(SurfaceHolder holder) {
            // surface뷰에서 넘겨준 홀더를 가지고 작업을 한다.
            surfaceHolder = holder;

        }

        @Override
        public void run() {
            // 무한반복 하면서 그림을 그려준다.

            while(running) {
                // 그림을 그릴 캔버스를 가져오고
                Canvas canvas = surfaceHolder.lockCanvas();

                // 그림을 그림
                synchronized (surfaceHolder) {
                    if(x > 0) {
                    paint.setColor(Color.WHITE);
                    canvas.drawRect(0,0,deviceWidth,deviceHeight, paint);
                    paint.setColor(Color.BLUE);
                    canvas.drawRect(x,y,x+50,y+50,paint);
                }

                x++; y++;

                if (x > deviceWidth) {
                    x = 0;
                    y = 0;
                }

                // 여기서 실제 디스플레이에 그려주게 된다
                surfaceHolder.unlockCanvasAndPost(canvas);
            }
        }
    }
}
