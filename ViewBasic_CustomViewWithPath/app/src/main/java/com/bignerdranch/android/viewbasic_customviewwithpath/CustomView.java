package com.bignerdranch.android.viewbasic_customviewwithpath;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.View;


class CustomView extends View {
    Paint paint = new Paint();
    Path path = new Path();

    public CustomView(Context context) {
        super(context);

        paint.setColor(Color.MAGENTA);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(10f);
    }


    public void reset() {
        path = new Path();
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
       canvas.drawPath(path, paint);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                path.moveTo(x,y); // 시작점을 표시

                break;
            case MotionEvent.ACTION_MOVE:

                path.lineTo(x,y); // 이동경로상에 라인을 그려준다
                break;

        }

        invalidate();

        return true;
    }
}