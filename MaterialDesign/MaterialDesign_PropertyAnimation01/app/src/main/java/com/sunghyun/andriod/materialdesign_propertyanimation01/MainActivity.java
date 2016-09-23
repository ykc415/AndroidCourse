package com.sunghyun.andriod.materialdesign_propertyanimation01;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Toast;

/*
    ObjectAnimator 사용 법
    1. 애니메이터를 정의한다                          대상개체 ,  개체의 속성    , 속성값 숫자
    ObjectAnimator ani = ObjectAnimator.ofFloat(player, "translationY", y);

    2. 정의된 애니메이터를 실행한다
    ani.start();
 */

public class MainActivity extends AppCompatActivity {

    RelativeLayout ground;
    ImageButton player;
    int x = 0;
    int y = 0;
    int gx = 0;
    int gy = 0;
    int px = 0;
    int py = 0;
    int r = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        player = (ImageButton) findViewById(R.id.player);
        ground = (RelativeLayout) findViewById(R.id.ground);


    }

    private void setGroundSize() {
        gx = ground.getWidth();
        gy = ground.getHeight();
        px = player.getWidth()/2;
        py = player.getHeight()/2;
        Log.i("Mainactivity onResume","gx="+gx+","+"gy="+gy);
    }



    public void up(View v) {
        setGroundSize();
        y = y-50;

        if(-(gy/2)+py <= y) {
            ObjectAnimator ani = ObjectAnimator.ofFloat(player, "translationY", y);
            ani.start();
        }else {
            y=y+50;
        }

    }
    public void down(View v) {
        setGroundSize();
        y = y+50;
        if(gy/2-py >= y) {
            ObjectAnimator ani = ObjectAnimator.ofFloat(player, "translationY", y);
            ani.start();
        }else {
            y = y - 50;
        }

    }
    public void left(View v) {
        setGroundSize();
        x = x-50;
        if(-(gx/2)+px <= x) {
            ObjectAnimator ani = ObjectAnimator.ofFloat(player, "translationX", x);
            ani.start();
        }else {
            x = x + 50;
        }

    }
    public void right(View v) {
        setGroundSize();
        x= x+50;
        if(gx/2-py >= x) {
            ObjectAnimator ani = ObjectAnimator.ofFloat(player, "translationX", x);
            ani.start();
        }else {
            x = x - 50;
        }
    }

    float scale_x = 1;
    float scale_y = 1;
    public void smaller(View v) {
        scale_x = scale_x / 2;
        scale_y = scale_y / 2;
        ObjectAnimator ani1 = ObjectAnimator.ofFloat(player, "scaleX", scale_x); // 배수
        ObjectAnimator ani2 = ObjectAnimator.ofFloat(player, "scaleY", scale_y); // 배수

        //여러개의 애니메이션 동시에 사용하기
        // 1. AnimatorSet을 초기화한다.
        AnimatorSet aniSet = new AnimatorSet();
        // 2. 동작시간을 생성한다
        aniSet.setDuration(1000);
        // 3. playTogether에 애니메이션을 담아준다.
        aniSet.playTogether(ani1,ani2);
        // 4. 애니메이션을 실핸한다.
        aniSet.start();

    }

    public void rotate(View v) {
        r= r+90;
        ObjectAnimator ani = ObjectAnimator.ofFloat(player, "rotation", r);
        ani.start();
    }

    public void bigger(View v) {
        scale_x = scale_x * 2;
        scale_y = scale_y * 2;
        ObjectAnimator ani1 = ObjectAnimator.ofFloat(player, "scaleX", scale_x); // 배수
        ObjectAnimator ani2 = ObjectAnimator.ofFloat(player, "scaleY", scale_y); // 배수

        AnimatorSet aniSet = new AnimatorSet();
        aniSet.setDuration(1000);
        aniSet.playTogether(ani1,ani2);
        aniSet.start();
    }




    public void showMessage(View v) {
        Toast.makeText(this, "I am Here!!!", Toast.LENGTH_SHORT).show();

    }
}
