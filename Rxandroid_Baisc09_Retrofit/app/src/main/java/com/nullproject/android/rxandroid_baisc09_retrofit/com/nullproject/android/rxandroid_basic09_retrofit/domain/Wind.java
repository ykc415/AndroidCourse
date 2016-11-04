package com.nullproject.android.rxandroid_baisc09_retrofit.com.nullproject.android.rxandroid_basic09_retrofit.domain;

/**
 * Created by YKC on 2016. 11. 4..
 */

public class Wind
{
    private String speed;

    private String deg;

    public String getSpeed ()
    {
        return speed;
    }

    public void setSpeed (String speed)
    {
        this.speed = speed;
    }

    public String getDeg ()
    {
        return deg;
    }

    public void setDeg (String deg)
    {
        this.deg = deg;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [speed = "+speed+", deg = "+deg+"]";
    }
}

