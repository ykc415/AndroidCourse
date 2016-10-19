package com.kodonho.android.threadbasic_tetris;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Handler;
import android.view.View;

/**
 * Created by fastcampus on 2016-10-17.
 */
public class Stage extends View {
    public static final int REFRESH = 0;
    public static int interval = 1000;

    Paint paint[] = new Paint[10];

    Context context;

    Handler mainHandler;

    int unit = 0;
    final int stageWidth = 14;
    final int stageHeight = 21;
    final int stageTop = 1;
    final int stageLeft = 1;

    final int previewWidth = 6;
    final int previewHeight = 6;
    final int previewTop = 1;
    final int previewLeft = 16;

    // 스테이지에서 현재 움직이고 있는 블럭
    Block blockGroup = null;

    public void setFirstBlock() {
        //생성자에서 호출됨
        blockGroup = BlockFactory.newBlock(mainHandler);
        blockGroup.start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // 스테이지를 그린다
        for(int i=0;i<stageWidth;i++){
            for(int j=0;j<stageHeight;j++){
                canvas.drawRect(
                        (stageLeft + i) * unit
                       ,(stageTop + j) * unit
                       ,(stageLeft + i) * unit + unit
                       ,(stageTop + j) * unit + unit
                       , paint[stageOne[j][i]]
                );
            }
        }

        // 프리뷰를 그린다
        for(int i=0;i<previewWidth;i++){
            for(int j=0;j<previewHeight;j++){
                canvas.drawRect(
                        (previewLeft + i) * unit
                        ,(previewTop + j) * unit
                        ,(previewLeft + i) * unit + unit
                        ,(previewTop + j) * unit + unit
                        , paint[previewMap[j][i]]
                );
            }
        }

        // 현재 회전방향이 결정된 블럭을 가져온다.
        int cBlock[][] = blockGroup.getBlock();

        // 해당 블럭을 그린다
        for(int i = 0; i< blockGroup.width; i++){
            for(int j = 0; j< blockGroup.height; j++){
                if(cBlock[j][i] > 0) { // 블럭에 입력된 값이 0 보다 클경우만 그려준다
                    canvas.drawRect(
                            (stageLeft + blockGroup.x) * unit
                            , (stageTop + blockGroup.y) * unit
                            , (stageLeft + blockGroup.x) * unit + unit
                            , (stageTop + blockGroup.y) * unit + unit
                            , paint[stageOne[j][i]]
                    );
                }
            }
        }
    }

    int previewMap[][] = {
            {9,9,9,9,9,9},
            {9,0,0,0,0,9},
            {9,0,0,0,0,9},
            {9,0,0,0,0,9},
            {9,0,0,0,0,9},
            {9,9,9,9,9,9}
    };

    int stageOne[][] = {
            {9,0,0,0,0,0,0,0,0,0,0,0,0,9},
            {9,0,0,0,0,0,0,0,0,0,0,0,0,9},
            {9,0,0,0,0,0,0,0,0,0,0,0,0,9},
            {9,0,0,0,0,0,0,0,0,0,0,0,0,9},
            {9,0,0,0,0,0,0,0,0,0,0,0,0,9},
            {9,0,0,0,0,0,0,0,0,0,0,0,0,9},
            {9,0,0,0,0,0,0,0,0,0,0,0,0,9},
            {9,0,0,0,0,0,0,0,0,0,0,0,0,9},
            {9,0,0,0,0,0,0,0,0,0,0,0,0,9},
            {9,0,0,0,0,0,0,0,0,0,0,0,0,9},
            {9,0,0,0,0,0,0,0,0,0,0,0,0,9},
            {9,0,0,0,0,0,0,0,0,0,0,0,0,9},
            {9,0,0,0,0,0,0,0,0,0,0,0,0,9},
            {9,0,0,0,0,0,0,0,0,0,0,0,0,9},
            {9,0,0,0,0,0,0,0,0,0,0,0,0,9},
            {9,0,0,0,0,0,0,0,0,0,0,0,0,9},
            {9,0,0,0,0,0,0,0,0,0,0,0,0,9},
            {9,0,0,0,0,0,0,0,0,0,0,0,0,9},
            {9,0,0,0,0,0,0,0,0,0,0,0,0,9},
            {9,0,0,0,0,0,0,0,0,0,0,0,0,9},
            {9,9,9,9,9,9,9,9,9,9,9,9,9,9}
    };

    public Stage(Context context, Handler handler, int unit) {
        super(context);

        mainHandler = handler;
        this.unit = unit;

        for(int i=0;i<paint.length;i++){
            paint[i] = new Paint();
        }

        paint[0].setColor(getResources().getColor(R.color.background));
        paint[1].setColor(getResources().getColor(R.color.block1));
        paint[2].setColor(getResources().getColor(R.color.block2));
        paint[3].setColor(getResources().getColor(R.color.block3));
        paint[4].setColor(getResources().getColor(R.color.block4));
        paint[5].setColor(getResources().getColor(R.color.block5));
        paint[6].setColor(getResources().getColor(R.color.block6));
        paint[7].setColor(getResources().getColor(R.color.block7));
        paint[9].setColor(getResources().getColor(R.color.border));
    }


}
