package com.bignerdranch.android.viewbasic_custom;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new CustomView(this));
    }
}


class CustomView extends View {



    public CustomView(Context context) {
        super(context);

    }


    @Override
    protected void onDraw(Canvas canvas) {
        //super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setColor(Color.MAGENTA);
                        //left,top,right,bottom
        //canvas.drawRect(0,0,300,300,paint);

        if(x >= 0) {
            canvas.drawCircle(x, y, radius, paint);
        }
    }

    int x = -1;
    int y = -1;
    int radius = 100;
    Rect rect = new Rect(0,0,10,10);
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //return super.onTouchEvent(event);
        switch (event.getAction()) {
            case MotionEvent.ACTION_UP:
                x = (int)event.getX();
                y = (int)event.getY();

                invalidate(rect);
                break;

        }

        return true;
    }
}