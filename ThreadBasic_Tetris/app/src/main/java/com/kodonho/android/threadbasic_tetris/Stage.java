package com.kodonho.android.threadbasic_tetris;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Handler;
import android.util.Log;
import android.view.View;

/**
 * Created by fastcampus on 2016-10-17.
 */
public class Stage extends View {
    public static final int REFRESH = 0;
    public static final int NEW_BLOCK = 1;
    public static int interval = 300;
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

    // 현재 화면에 그려지는 스테이지
    static int stageMap[][] = null;
    //스테이지에서 현재 움직이고 있는 블럭
    Block blockGroup = null;

    Block nextBlock = null;

    static int previewMap[][] = null;

    public void setBlock(){
        // 생성자에서 호출된다
        blockGroup = BlockFactory.newBlock(mainHandler);
        nextBlock = BlockFactory.newBlock(mainHandler);
        blockGroup.start();
    }

    // 스테이지를 갱신한다
    public void setStage(){
        stageMap = stageOne.clone();
    }

    public void setPreview() {
        previewMap = preview.clone();
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
                       , paint[stageMap[j][i]]
                );
            }
        }

        //TODO previewMap에 nextblock  을그려줌
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

        // 현재 회전방향이 결정된 블럭을 가져온다
        int cBlock[][] = blockGroup.getBlock();

        // 해당 블럭을 그려준다
        Log.i("DrawBlock","============================================");
        for(int i=0;i<blockGroup.width;i++){
            for(int j=0;j<blockGroup.height;j++){
                Log.i("DrawBlock","x="+i+", y="+j+", cBlock[j][i]="+cBlock[j][i]);
                if(cBlock[j][i] > 0) {  // 블럭에 입력된 값이 0보다 클 경우만 그려준다
                    canvas.drawRect(
                              (stageLeft + blockGroup.x + i) * unit
                        // stage 왼쪽여백 + block 의 x좌표 + 각 셀의 x좌표 증가값
                            , (stageTop + blockGroup.y + j) * unit
                        // stage 윗쪽여백 + block 의 y좌표 + 각 셀의 y좌표 증가값
                            , (stageLeft + blockGroup.x + i) * unit + unit
                            , (stageTop + blockGroup.y + j) * unit + unit
                            , paint[cBlock[j][i]]
                    );
                }
            }
        }
    }

    int preview[][] = {
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

        setStage();
        setBlock();
        setPreview();
    }

    public void leftBlock(){
        blockGroup.x--;
        if(!blockGroup.collisionCheck()){
            invalidate();
        }else{
            blockGroup.x++;
        }
    }

    public void rightBlock(){
        blockGroup.x++;
        if(!blockGroup.collisionCheck()){
            invalidate();
        }else{
            blockGroup.x--;
        }
    }

    public void downBlock(){
        synchronized (blockGroup) {
            blockGroup.y++;
            if (!blockGroup.collisionCheck()) {
                invalidate();
            } else {
                blockGroup.y--;
                blockGroup.setBlockIntoStage();
            }
        }
    }

    public void rotateBlock(){
        blockGroup.rotate();
        if(!blockGroup.collisionCheck()){
            invalidate();
        }else{
            blockGroup.rotateBack();
        }
    }

}
