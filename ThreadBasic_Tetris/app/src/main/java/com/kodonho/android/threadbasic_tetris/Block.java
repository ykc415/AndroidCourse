package com.kodonho.android.threadbasic_tetris;

import android.os.Handler;

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

    public int[][] getBlock() {
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
    }

    // 이동
    public void move(int x, int y){
        this.x = x;
        this.y = y;
    }

    // 생성되고 화면에 세팅되면
    public void run(){
        while(alive) {
            try{
                // 시작하자마자는 위치 변경이 없으므로 먼저 sleep을 한다
                Thread.sleep(interval);
                y++;
                // 위치 병경 후에는 다시 그리기를 요청한다
                handler.sendEmptyMessage(Stage.REFRESH);
            }catch(Exception e){

            }
        }
    }
}
