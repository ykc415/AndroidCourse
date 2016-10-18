package com.bignerdranch.android.custonview_rectai;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private final static int GROUND_LIMIT = 10;

    FrameLayout ground ;
    Button left, up, right, down;
    Button start;
    CustomView cv;

    private int deviceWidth = 0;
    private int unit = 0;

    private int player_x = 0;
    private int player_y = 0;

    private int enemy_x = 0;
    private int enemy_y = 0;

    Enemy enemy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DisplayMetrics dp = getResources().getDisplayMetrics();
        deviceWidth = dp.widthPixels;
        unit = deviceWidth / GROUND_LIMIT;


        ground = (FrameLayout) findViewById(R.id.playGround);
        left = (Button) findViewById(R.id.button_left);
        up = (Button) findViewById(R.id.button_up);
        right = (Button) findViewById(R.id.button_right);
        down = (Button) findViewById(R.id.button_down);
        start = (Button) findViewById(R.id.button_start);

        left.setOnClickListener(this);
        up.setOnClickListener(this);
        right.setOnClickListener(this);
        down.setOnClickListener(this);
        start.setOnClickListener(this);

        cv = new CustomView(this);
        ground.addView(cv);

    }





    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.button_up:
                player_y = player_y + checkCollision("y", -1);
                break;
            case R.id.button_down:
                player_y = player_y + checkCollision("y", +1);
                break;
            case R.id.button_left:
                player_x = player_x + checkCollision("x", -1);
                break;
            case R.id.button_right:
                player_x = player_x + checkCollision("x", +1);
                break;
            case R.id.button_start:
                enemy = new Enemy(cv);
                enemy.start();
                break;
        }
        cv.invalidate();

    }

    private int checkCollision(String direction ,int nextValue) {



        if(direction.equals("y")) {
            if((player_y + nextValue) <0 || (player_y + nextValue) >= GROUND_LIMIT)
                return 0;
        }

        if(direction.equals("x")) {
            if((player_x + nextValue) <0 || (player_x + nextValue) >= GROUND_LIMIT)
                return 0;
        }


        return nextValue;
    }




    class CustomView extends View {

        Paint paint = new Paint();

        public void moveEnemy(int offset) {
            if(player_x*unit == enemy_x && player_y*unit == enemy_y ) {
                enemy.interrupt();

            }

            if(player_x*unit > enemy_x) {
                enemy_x = enemy_x + offset;
            }else if(player_x*unit < enemy_x) {
                enemy_x = enemy_x - offset;
            }

            if(player_y*unit > enemy_y) {
                enemy_y = enemy_y + offset;
            }else if (player_y*unit < enemy_y) {
                enemy_y = enemy_y - offset;
            }
        }
        public CustomView(Context context) {
            super(context);

            paint.setColor(Color.MAGENTA);

        }
        public void drawEnemy(Canvas canvas) {
            paint.setColor(Color.RED);
            canvas.drawRect(enemy_x*unit,enemy_y*unit,enemy_x*unit+unit,enemy_y*unit+unit,paint);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            paint.setColor(Color.DKGRAY);
            canvas.drawRect(0,0,deviceWidth,deviceWidth,paint);

            paint.setColor(Color.RED);
            canvas.drawRect(enemy_x,enemy_y,enemy_x+unit,enemy_y+unit,paint);


            paint.setColor(Color.BLUE);
            canvas.drawRect(player_x*unit,player_y*unit,player_x*unit+unit,player_y*unit+unit,paint);
        }


    }


    class Enemy extends Thread {

        CustomView cv;


        public Enemy(CustomView cv) {
            this.cv = cv;

        }

        @Override
        public void run() {
            int limit = 0;
            while(limit < 1000) {
                // cv 에 그려지는 사각형의 좌표값을 조작한다.
                cv.moveEnemy(1);
                cv.postInvalidate();

                limit++;


                try { Thread.sleep(20);}catch(Exception e) { e.printStackTrace(); }

            }
        }

    }
}
