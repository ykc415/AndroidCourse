package com.sunghyun.andriod.materialdesign_propertyanimaionwindmill;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {


    ImageButton[] mButtons = new ImageButton[4];
    Button mbutton;
    RelativeLayout mLayout;

    boolean misButtonClicked = false;
    int bs_x = 0;
    int bs_y = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mbutton = (Button) findViewById(R.id.button);
        mLayout = (RelativeLayout) findViewById(R.id.relativeLayout);

        mButtons[0] = (ImageButton) findViewById(R.id.imageButton1);
        mButtons[1] = (ImageButton) findViewById(R.id.imageButton2);
        mButtons[2] = (ImageButton) findViewById(R.id.imageButton3);
        mButtons[3] = (ImageButton) findViewById(R.id.imageButton4);



    }


    private void getButtonSize() {
        bs_y = mButtons[0].getHeight()/2;
        bs_x = mButtons[0].getWidth()/2;
    }

    int[] x = new int[4];
    int[] y = new int[4];


    public void onClick(View v) {

        getButtonSize();
        translate();
        rotate();
        sizeChange();

        misButtonClicked = !misButtonClicked;
    }


    ObjectAnimator[] anix = new ObjectAnimator[4];
    ObjectAnimator[] aniy = new ObjectAnimator[4];
    AnimatorSet[] aniSet = new AnimatorSet[4];
    AnimatorSet[] rotateAniSet = new AnimatorSet[4];
    AnimatorSet[] sizeAniSet = new AnimatorSet[4];
    private void translate() {
        x[0] = bs_x; y[0] = bs_y;
        x[1] = bs_x; y[1] = -bs_y;
        x[2] = -bs_x; y[2] = bs_y;
        x[3] = -bs_x; y[3] = -bs_y;


        for(int i = 0;i < 4;i++) {


            if(!misButtonClicked) {
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


        for(int i =0;i<4;i++) {
            aniSet[i].start();
        }
    }



    int r = 0;
    public void rotate() {
        r = r+360;
        ObjectAnimator ani = ObjectAnimator.ofFloat(mLayout, "rotation", r);
        ani.setDuration(10000);
        ani.start();


    }


    public void sizeChange() {
//        scale_x = scale_x * 2;
//        scale_y = scale_y * 2;
//        ObjectAnimator ani1 = ObjectAnimator.ofFloat(player, "scaleX", scale_x); // 배수
//        ObjectAnimator ani2 = ObjectAnimator.ofFloat(player, "scaleY", scale_y); // 배수
//
//        AnimatorSet sizeAniSet = new AnimatorSet();
//        sizeAniSet.setDuration(1000);
//        sizeAniSet.playTogether(ani1,ani2);
//        sizeAniSet.start();
    }
}

