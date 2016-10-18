package com.bignerdranch.android.threadbasic_tetris;

import android.os.Handler;

/**
 * Created by YKC on 2016. 10. 17..
 */
public class Block extends Thread{

    int x = 0;
    int y = 0;
    int orientation_limit = 0;
    int orientation = 0;
    private int blocktype[][][];
    private int block[][];

    boolean alive = true;
    int interval = 0;
    Handler handler;

    public void draw() {
        handler.sendEmptyMessage(Stage.REFRESH);
    }


    public Block(int[][][] blocktype, int interval, Handler handler) {
        this.blocktype = blocktype;
        this.interval = interval;
        block = this.blocktype[orientation];
        orientation_limit = this.blocktype.length;
        this.handler = handler;
    }

    public void run() {
        while(alive) {
            try{
                y++;
                Thread.sleep(interval);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public void rotate() {
        orientation++;
        orientation = orientation % orientation_limit;

    }

    public void move(int x, int y) {
        this.x = x;
        this.y = y;
    }

}
