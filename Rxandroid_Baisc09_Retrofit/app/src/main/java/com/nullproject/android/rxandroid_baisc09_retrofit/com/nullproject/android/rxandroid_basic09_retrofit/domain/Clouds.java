package com.nullproject.android.rxandroid_baisc09_retrofit.com.nullproject.android.rxandroid_basic09_retrofit.domain;

/**
 * Created by YKC on 2016. 11. 4..
 */

public class Clouds
{
    private String all;

    public String getAll ()
    {
        return all;
    }

    public void setAll (String all)
    {
        this.all = all;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [all = "+all+"]";
    }
}