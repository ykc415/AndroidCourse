package com.bignerdranch.android.threadbasic_tetris;

import java.util.Random;

/**
 * Created by YKC on 2016. 10. 18..
 */

public class BlockFactory {
    private static final int ABS_X = 5;
    private static final int ABS_Y = 0;


    int x = 0;
    int y = 0;

    private int currentOrientation = 0;
    private int currentOrientationLimit = 0;
    private int[][][] currentBlockGroup;
    private int[][] currentBlock;

    public void setBlock() {
        Random random = new Random();
        int type = random.nextInt(7);

        // 블럭의 회전상태를 포함한 블럭 그룹을 선택
        currentBlockGroup = block[type];

        currentOrientation = 0;
        currentOrientationLimit = currentBlockGroup.length;
        currentBlock = currentBlockGroup[currentOrientation];

        //최초에 세팅되는 블럭의 위치
        x = ABS_X;
        y = ABS_Y;
    }

    public void rotateBlock() {
        currentOrientation++;
        currentOrientation = currentOrientation % currentOrientationLimit;
        currentBlock = currentBlockGroup[currentOrientation];
    }

    public int[][] getCurrentBlock() {
        return currentBlock;
    }

    int block[][][][] = {
            {
                    // Block I
                    {
                            {0, 1, 0, 0},
                            {0, 1, 0, 0},
                            {0, 1, 0, 0},
                            {0, 1, 0, 0}
                    },
                    {
                            {0, 0, 0, 0},
                            {1, 1, 1, 1},
                            {0, 0, 0, 0},
                            {0, 0, 0, 0}
                    },
                    {
                            {0, 1, 0, 0},
                            {0, 1, 0, 0},
                            {0, 1, 0, 0},
                            {0, 1, 0, 0}
                    },
                    {
                            {0, 0, 0, 0},
                            {1, 1, 1, 1},
                            {0, 0, 0, 0},
                            {0, 0, 0, 0}
                    }
            },
            // Block J
            {
                    {
                            {0, 0, 2, 0},
                            {0, 0, 2, 0},
                            {0, 2, 2, 0},
                            {0, 0, 0, 0}
                    },
                    {
                            {0, 0, 0, 0},
                            {0, 2, 0, 0},
                            {0, 2, 2, 2},
                            {0, 0, 0, 0}
                    },
                    {
                            {0, 2, 2, 0},
                            {0, 2, 0, 0},
                            {0, 2, 0, 0},
                            {0, 0, 0, 0}
                    },
                    {
                            {0, 0, 0, 0},
                            {2, 2, 2, 0},
                            {0, 0, 2, 0},
                            {0, 0, 0, 0}
                    }
            },
            // Block L
            {
                    {
                            {0, 3, 0, 0},
                            {0, 3, 0, 0},
                            {0, 3, 3, 0},
                            {0, 0, 0, 0}
                    },
                    {
                            {0, 0, 0, 0},
                            {0, 3, 3, 3},
                            {0, 3, 0, 0},
                            {0, 0, 0, 0}
                    },
                    {
                            {0, 3, 3, 0},
                            {0, 0, 3, 0},
                            {0, 0, 3, 0},
                            {0, 0, 0, 0}
                    },
                    {
                            {0, 0, 3, 0},
                            {3, 3, 3, 0},
                            {0, 0, 0, 0},
                            {0, 0, 0, 0}
                    }
            },
            // Block S
            {
                    {
                            {0, 0, 0, 0},
                            {0, 4, 4, 0},
                            {4, 4, 0, 0},
                            {0, 0, 0, 0}
                    },
                    {
                            {0, 4, 0, 0},
                            {0, 4, 4, 0},
                            {0, 0, 4, 0},
                            {0, 0, 0, 0}
                    },
                    {
                            {0, 0, 0, 0},
                            {0, 4, 4, 0},
                            {4, 4, 0, 0},
                            {0, 0, 0, 0}
                    },
                    {
                            {0, 4, 0, 0},
                            {0, 4, 4, 0},
                            {0, 0, 4, 0},
                            {0, 0, 0, 0}
                    }
            },
            // Block Z
            {
                    {
                            {0, 0, 0, 0},
                            {0, 5, 5, 0},
                            {0, 0, 5, 5},
                            {0, 0, 0, 0}
                    },
                    {
                            {0, 0, 5, 0},
                            {0, 5, 5, 0},
                            {0, 5, 0, 0},
                            {0, 0, 0, 0}
                    },
                    {
                            {0, 0, 0, 0},
                            {0, 5, 5, 0},
                            {0, 0, 5, 5},
                            {0, 0, 0, 0}
                    },
                    {
                            {0, 0, 5, 0},
                            {0, 5, 5, 0},
                            {0, 5, 0, 0},
                            {0, 0, 0, 0}
                    }
            },
            // Block T
            {
                    {
                            {0, 6, 0, 0},
                            {0, 6, 6, 0},
                            {0, 6, 0, 0},
                            {0, 0, 0, 0}
                    },
                    {
                            {0, 0, 0, 0},
                            {6, 6, 6, 0},
                            {0, 6, 0, 0},
                            {0, 0, 0, 0}
                    },
                    {
                            {0, 6, 0, 0},
                            {6, 6, 0, 0},
                            {0, 6, 0, 0},
                            {0, 0, 0, 0}
                    },
                    {
                            {0, 6, 0, 0},
                            {6, 6, 6, 0},
                            {0, 0, 0, 0},
                            {0, 0, 0, 0}
                    }
            },
            // Block O
            {
                    {
                            {0, 0, 0, 0},
                            {0, 7, 7, 0},
                            {0, 7, 7, 0},
                            {0, 0, 0, 0}
                    },
                    {
                            {0, 0, 0, 0},
                            {0, 7, 7, 0},
                            {0, 7, 7, 0},
                            {0, 0, 0, 0}
                    },
                    {
                            {0, 0, 0, 0},
                            {0, 7, 7, 0},
                            {0, 7, 7, 0},
                            {0, 0, 0, 0}
                    },
                    {
                            {0, 0, 0, 0},
                            {0, 7, 7, 0},
                            {0, 7, 7, 0},
                            {0, 0, 0, 0}
                    },
            },
    };
}
