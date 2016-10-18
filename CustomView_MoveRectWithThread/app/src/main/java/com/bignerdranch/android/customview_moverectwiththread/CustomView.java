package com.bignerdranch.android.customview_moverectwiththread;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.MotionEvent;
import android.view.View;


class CustomView extends View {

    private int x = 0;
    private int y = 0;

    private final static int WIDTH = 100;
    private final static int HEIGHT = 100;

    Paint paint = new Paint();

    public CustomView(Context context) {
        super(context);

        paint.setColor(Color.MAGENTA);

    }


    public void reset() {

        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
       canvas.drawRect(0,0,x+WIDTH,y+HEIGHT,paint);
    }

    public void moveRect(int offset) {
        x = x + offset;
        y = y + offset;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:


                break;
            case MotionEvent.ACTION_MOVE:


                break;

        }

        invalidate();

        return true;
    }
}

class CustomThread extends Thread {

    CustomView cv;
    private static final int OFFSET = 2;

    public CustomThread(CustomView cv) {
        this.cv = cv;

    }

    @Override
    public void run() {
        int limit = 0;
        while(limit < 1000) {
            // cv 에 그려지는 사각형의 좌표값을 조작한다.
            cv.moveRect(OFFSET);
            cv.postInvalidate();
            limit++;
            try { Thread.sleep(100);}catch(Exception e) { e.printStackTrace(); }

        }
    }

}







