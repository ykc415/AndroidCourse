package com.bignerdranch.android.customview_pushpush;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private final static int GROUND_LIMIT = 10;

    FrameLayout ground ;
    Button left, up, right, down;
    CustomView cv;

    private int deviceWidth = 0;
    private int unit = 0;

    private int player_x = 0;
    private int player_y = 0;

    int map[][] = {
            {0,0,0,0,0,0,1,0,0,0},
            {0,1,1,1,1,0,1,0,0,0},
            {0,0,0,0,1,0,1,0,0,0},
            {0,0,0,0,1,0,1,0,0,0},
            {1,1,0,0,0,0,1,0,0,0},
            {0,0,1,0,1,1,1,1,0,0},
            {0,0,1,0,0,0,0,1,0,0},
            {0,0,1,0,0,0,0,1,0,0},
            {0,0,1,1,1,1,0,1,1,0},
            {0,0,0,0,0,1,0,0,0,0}
    };



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

        left.setOnClickListener(this);
        up.setOnClickListener(this);
        right.setOnClickListener(this);
        down.setOnClickListener(this);


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

        if(direction.equals("y")) {
            int temp_y = player_y+nextValue;
            if(map[temp_y][player_x] == 1) {
                if (temp_y + nextValue < 0
                        || temp_y + nextValue >= GROUND_LIMIT
                        || map[temp_y + nextValue][player_x] != 0)

                    return 0;
                else {
                    map[temp_y][player_x] = 0;
                    map[temp_y + nextValue][player_x] = 1;
                }

            }
        }

        if(direction.equals("x")) {
            int temp_x = player_x+nextValue;
            if(map[player_y][temp_x] == 1) {
                if (temp_x + nextValue < 0
                        || temp_x + nextValue >= GROUND_LIMIT
                        || map[player_y][temp_x + nextValue] != 0)

                    return 0;
                else {
                    map[player_y][temp_x] = 0;
                    map[player_y][temp_x + nextValue] = 1;
                }
            }
        }

        return nextValue;
    }


    class CustomView extends View {

        Paint paint = new Paint();

        public CustomView(Context context) {
            super(context);

            paint.setColor(Color.MAGENTA);

        }


        @Override
        protected void onDraw(Canvas canvas) {
            paint.setColor(Color.CYAN);
            canvas.drawRect(0,0,deviceWidth,deviceWidth,paint);

            paint.setColor(Color.BLACK);
            for(int i=0;i<GROUND_LIMIT;i++) {
                for(int j=0;j<GROUND_LIMIT;j++) {
                    if(map[i][j] == 1) {
                        canvas.drawRect(j*unit,i*unit,j*unit+unit,i*unit+unit,paint);
                    }
                }
            }


            paint.setColor(Color.RED);
            canvas.drawRect(player_x*unit,player_y*unit,player_x*unit+unit,player_y*unit+unit,paint);
        }


    }
}
