package com.sunghyun.andriod.materialdesing_propertyanimaion_spreadcube;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

//// 1. 미리 정의된 애니메이션 xml을 로드한다.
//Animation animation = AnimationUtils.loadAnimation(MainActivity.this,R.anim.translate);
//        // 2. 애니메이션을 뷰에 적용하고 실행한다
//        btnTrans.startAnimation(animation);
//
//ObjectAnimator ani = ObjectAnimator.ofFloat(player, "translationY", y);
//        ani.start();
public class MainActivity extends AppCompatActivity {

    ImageButton[] mButtons = new ImageButton[9];
    Button mbutton;


    boolean misButtonClicked = false;
    int bs_x = 0;
    int bs_y = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mbutton = (Button) findViewById(R.id.button);

        mButtons[0] = (ImageButton) findViewById(R.id.imageButton1);
        mButtons[1] = (ImageButton) findViewById(R.id.imageButton2);
        mButtons[2] = (ImageButton) findViewById(R.id.imageButton3);
        mButtons[3] = (ImageButton) findViewById(R.id.imageButton4);
        mButtons[4] = (ImageButton) findViewById(R.id.imageButton5);
        mButtons[5] = (ImageButton) findViewById(R.id.imageButton6);
        mButtons[6] = (ImageButton) findViewById(R.id.imageButton7);
        mButtons[7] = (ImageButton) findViewById(R.id.imageButton8);
        mButtons[8] = (ImageButton) findViewById(R.id.imageButton9);


    }


    private void getButtonSize() {
        bs_y = mButtons[0].getHeight();
        bs_x = mButtons[0].getWidth();
    }

    int[] x = new int[9];
    int[] y = new int[9];


    public void onClick(View v) {


        getButtonSize();

        ObjectAnimator[] anix = new ObjectAnimator[9];
        ObjectAnimator[] aniy = new ObjectAnimator[9];

        AnimatorSet[] aniSet = new AnimatorSet[9];

//        x[0] = bs_x;
//        y[0] = bs_y;
//        x[1] = bs_x;
//        y[1] = 0;
//        x[2] = bs_x;
//        y[2] = -bs_y;
//        x[3] = 0;
//        y[3] = bs_y;
//        x[4] = 0;
//        y[4] = 0;
//        x[5] = 0;
//        y[5] = -bs_y;
//        x[6] = -bs_x;
//        y[6] = bs_y;
//        x[7] = -bs_x;
//        y[7] = 0;
//        x[8] = -bs_x;
//        y[8] = -bs_y;

            for (int k = 0; k < 9; ) {
                for (int j = 1; j > -2; j--) {
                    y[k] = j * bs_y;
                    k++;
                }
            }

            int a = 0;
            while (a < 9) {
                if (a < 3) {
                    x[a] = bs_x;
                    a++;
                    continue;
                } else if (a < 6) {
                    x[a] = 0;
                    a++;
                    continue;
                } else if (a < 9) {
                    x[a] = -bs_x;
                    a++;
                    continue;
                }

            }
//    이거너 위치 모두반대로
//        else {
//            for (int k = 0; k < 9; ) {
//                for (int j = 1; j > -2; j--) {
//                    y[k] = j * -bs_y;
//                    k++;
//                }
//            }
//
//            int a = 0;
//            while (a < 9) {
//                if (a < 3) {
//                    x[a] = -bs_x;
//                    a++;
//                    continue;
//                } else if (a < 6) {
//                    x[a] = 0;
//                    a++;
//                    continue;
//                } else if (a < 9) {
//                    x[a] = bs_x;
//                    a++;
//                    continue;
//                }
//
//            }
//
//        }

        for(int i = 0;i < 9;i++) {


            if(misButtonClicked) {
                anix[i] = ObjectAnimator.ofFloat(mButtons[i], "translationX", x[i]);
                aniy[i] = ObjectAnimator.ofFloat(mButtons[i], "translationY", y[i]);
            } else
            {
                anix[i] = ObjectAnimator.ofFloat(mButtons[i], "translationX", 0);
                aniy[i] = ObjectAnimator.ofFloat(mButtons[i], "translationY", 0);
            }
            aniSet[i]= new AnimatorSet();
            aniSet[i].playTogether(anix[i],aniy[i]);
            aniSet[i].setDuration(1000);
        }


        for(int i =0;i<9;i++) {
            aniSet[i].start();
        }

        misButtonClicked = !misButtonClicked;
    }



}
