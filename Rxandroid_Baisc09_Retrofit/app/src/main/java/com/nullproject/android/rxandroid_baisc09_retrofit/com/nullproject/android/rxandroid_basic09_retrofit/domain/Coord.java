package com.nullproject.android.rxandroid_baisc09_retrofit.com.nullproject.android.rxandroid_basic09_retrofit.domain;

/**
 * Created by YKC on 2016. 11. 4..
 */


public class Coord
{
    private String lon;

    private String lat;

    public String getLon ()
    {
        return lon;
    }

    public void setLon (String lon)
    {
        this.lon = lon;
    }

    public String getLat ()
    {
        return lat;
    }

    public void setLat (String lat)
    {
        this.lat = lat;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [lon = "+lon+", lat = "+lat+"]";
    }
}