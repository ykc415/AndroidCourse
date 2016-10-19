package com.kodonho.android.threadbasic_tetris;

import android.os.Handler;
import android.util.Log;

/**
 * Created by fastcampus on 2016-10-17.
 */
public class Block extends Thread{
    int x = 5;
    int y = 0;
    int width = 4;
    int height = 4;

    int orientation_limit = 0;
    int orientation = 0;
    private int blocktype[][][];
    private int block[][];

    boolean alive = true;
    int interval = 0;
    Handler handler;

    public void draw(){
        handler.sendEmptyMessage(Stage.REFRESH);
    }

    public int[][] getBlock(){
        return block;
    }

    public Block(int[][][] blocktype, int interval, Handler handler){
        this.blocktype = blocktype;
        this.interval = interval;
        orientation_limit = this.blocktype.length;
        block = this.blocktype[orientation];
        this.handler = handler;
    }

    // 회전
    public void rotate(){
        orientation++;
        orientation = orientation%orientation_limit;
        block = blocktype[orientation];
    }

    // 회전 거꾸로
    public void rotateBack(){
        orientation--;
        block = blocktype[orientation];
    }

    // 생성되고 화면에 세팅되면
    public void run(){
        while(alive) {
            try{
                // 시작하자마자는 위치 변경이 없으므로 먼저 sleep을 한다
                Thread.sleep(interval);
                synchronized (this) {
                    y++;
                    if (!collisionCheck()) {
                        //위치 변경후에는 다시 그리기를 요청한다
                        handler.sendEmptyMessage(Stage.REFRESH);
                    } else {
                        y--;
                        setBlockIntoStage();
                    }
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }

    public void setBlockIntoStage(){
        alive = false;
        for(int i=0 ;i < width ; i++){
            for(int j=0;j < width ; j++){
                // 현재 블럭의 셀의 값을 가져온다
                int cBlockValue = block[j][i];
                if( cBlockValue > 0){ // 현재 블럭 셀의 값이 0보다 클경우만 스테이지에 담는다
                    // 스테이지 셀에 블럭셀의 값을 담아준다
                    Stage.stageMap[y+j][x+i] = cBlockValue;
                }
            }
        }

        // 블럭을 스테이지에 입력한 후에 해당 블럭범위에 있는 스테이지 가로줄이 꽉찼으면 지워준다
        for(int i=y ; i < y+4 ; i++){
            // 전체 스테이지 height값보다 작을때만
            if(i < 20) {
                // 스테이지 맵에서 한줄식 꺼낸다
                int row[] = Stage.stageMap[i];
                int zeroCount = 0;
                // 각 로우의 셀에 0이 있는지 검사
                for (int j = 0; j < row.length; j++) {
                    if (row[j] == 0) {
                        zeroCount++;
                    }
                }
                // 각로우의 셀에 0이 한개도 없으면
                if (zeroCount == 0) {
                    // 해당줄을 지운다

                    for (int k = 1; k < row.length-1; k++) {
                        Stage.stageMap[i][k] = 0;
                    }

                    Log.i("log","i ="+ i );
                    // 0~ i-1
                    for(int r = i ; r > 1  ; r--) {
                        Stage.stageMap[r] = Stage.stageMap[r - 1].clone();
                    }

                    //TODO  양옆 테두리 살리고 배열을 밑으로 내려줘야됨
                }
            }
        }
        this.interrupt();
        handler.sendEmptyMessage(Stage.NEW_BLOCK);
    }

    public boolean collisionCheck(){
        boolean result = false;
        for(int i=0 ;i < width ; i++){
            for(int j=0;j < height ; j++){
                // 현재 블럭의 셀의 값을 가져온다
                int cBlockValue = block[j][i];
                if( cBlockValue > 0){ // 현재 블럭 셀의 값이 0보다 클경우만 충돌체크를 한다
                    // 이동한곳의 stage 셀값과 block 의 셀값을 더한다
                    int sum = cBlockValue + Stage.stageMap[y+j][x+i];
                    if(sum > cBlockValue){ // 두개 셀을 더한값이 블럭셀의 값보다 크면 충돌
                        return true;
                    }
                }
            }
        }
        return result;
    }
}
