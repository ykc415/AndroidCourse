package com.kodonho.android.threadbasic_tetris;

import android.os.Handler;
import android.util.Log;

import java.util.Random;

/**
 * Created by fastcampus on 2016-10-18.
 */
public class BlockFactory {


    // 블럭을 생성하는 Factory Method
    public static Block newBlock(Handler handler){
        Random random = new Random();
        int blockIndex = random.nextInt(blocks.length);
        Block block = new Block(blocks[blockIndex], Stage.interval, handler);
        block.setDaemon(true);

        Log.i("New Block","type="+blockIndex +", interval="+Stage.interval+",orientation="+0);

        return block;
    }

    static int blocks[][][][] =
    {
        {
            {
                    {0, 1, 0, 0},
                    {0, 1, 0, 0},
                    {0, 1, 0, 0},
                    {0, 1, 0, 0}
            },
            {
                    {1, 1, 1, 1},
                    {0, 0, 0, 0},
                    {0, 0, 0, 0},
                    {0, 0, 0, 0}
            }
        },
        {
            {
                    {0, 2, 0, 0},
                    {2, 2, 2, 0},
                    {0, 0, 0, 0},
                    {0, 0, 0, 0}
            },
            {
                    {0, 2, 0, 0},
                    {0, 2, 2, 0},
                    {0, 2, 0, 0},
                    {0, 0, 0, 0},
            },
            {
                    {0, 0, 0, 0},
                    {2, 2, 2, 0},
                    {0, 2, 0, 0},
                    {0, 0, 0, 0},
            },
            {
                    {0, 2, 0, 0},
                    {2, 2, 0, 0},
                    {0, 2, 0, 0},
                    {0, 0, 0, 0},
            }
        },
        {
            {
                    {0, 3, 3, 0},
                    {0, 3, 3, 0},
                    {0, 0, 0, 0},
                    {0, 0, 0, 0}
            }
        },
        {
            {
                    {0, 4, 0, 0},
                    {4, 4, 0, 0},
                    {4, 0, 0, 0},
                    {0, 0, 0, 0}
            },
            {
                    {4, 4, 0, 0},
                    {0, 4, 4, 0},
                    {0, 0, 0, 0},
                    {0, 0, 0, 0},
            }
        },
        {
            {
                    {5, 0, 0, 0},
                    {5, 5, 0, 0},
                    {0, 5, 0, 0},
                    {0, 0, 0, 0}
            },
            {
                    {0, 5, 5, 0},
                    {5, 5, 0, 0},
                    {0, 0, 0, 0},
                    {0, 0, 0, 0},
            }
        },
        {
            {
                    {0, 6, 0, 0},
                    {0, 6, 6, 6},
                    {0, 0, 0, 0},
                    {0, 0, 0, 0}
            },
            {
                    {0, 6, 6, 0},
                    {0, 6, 0, 0},
                    {0, 6, 0, 0},
                    {0, 0, 0, 0},
            },
            {
                    {0, 6, 6, 6},
                    {0, 0, 0, 6},
                    {0, 0, 0, 0},
                    {0, 0, 0, 0}
            },
            {
                    {0, 0, 6, 0},
                    {0, 0, 6, 0},
                    {0, 6, 6, 0},
                    {0, 0, 0, 0},
            }
        },
        {
            {
                    {0, 0, 7, 0},
                    {7, 7, 7, 0},
                    {0, 0, 0, 0},
                    {0, 0, 0, 0}
            },
            {
                    {0, 7, 0, 0},
                    {0, 7, 0, 0},
                    {0, 7, 7, 0},
                    {0, 0, 0, 0},
            },
            {
                    {7, 7, 7, 0},
                    {7, 0, 0, 0},
                    {0, 0, 0, 0},
                    {0, 0, 0, 0}
            },
            {
                    {0, 7, 7, 0},
                    {0, 0, 7, 0},
                    {0, 0, 7, 0},
                    {0, 0, 0, 0},
            }
        }
    };

}
