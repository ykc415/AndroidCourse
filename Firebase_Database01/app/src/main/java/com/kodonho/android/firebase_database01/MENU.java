package com.kodonho.android.firebase_database01;

/**
 * Created by fastcampus on 2016-11-01.
 */

public class MENU
{
    public MENU(){

    }

    private String MENU_NAME;

    private long MENU_PRICE;

    private String MENU_IMAGE;

    public String getMENU_NAME (){
        return MENU_NAME;
    }

    public void setMENU_NAME (String MENU_NAME){
        this.MENU_NAME = MENU_NAME;
    }

    public long getMENU_PRICE (){
        return MENU_PRICE;
    }

    public void setMENU_PRICE (long MENU_PRICE){
        this.MENU_PRICE = MENU_PRICE;
    }

    public String getMENU_IMAGE (){
        return MENU_IMAGE;
    }

    public void setMENU_IMAGE (String MENU_IMAGE){
        this.MENU_IMAGE = MENU_IMAGE;
    }

    @Override
    public String toString(){
        return "ClassPojo [MENU_NAME = "+MENU_NAME+", MENU_PRICE = "+MENU_PRICE+", MENU_IMAGE = "+MENU_IMAGE+"]";
    }
}