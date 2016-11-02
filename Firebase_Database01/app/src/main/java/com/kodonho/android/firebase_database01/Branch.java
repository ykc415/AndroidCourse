package com.kodonho.android.firebase_database01;

import java.util.List;

/**
 * Created by fastcampus on 2016-11-01.
 */

public class Branch
{
    public Branch(){

    }

    private List<MENU> MENU;

    private String NAME;

    private String LOGO;

    private long DELIVERY_FEE;

    private String BRANCH;

    public List<MENU> getMENU ()
    {
        return MENU;
    }

    public void setMENU (List<MENU> MENU)
    {
        this.MENU = MENU;
    }

    public String getNAME ()
    {
        return NAME;
    }

    public void setNAME (String NAME)
    {
        this.NAME = NAME;
    }

    public String getLOGO ()
    {
        return LOGO;
    }

    public void setLOGO (String LOGO)
    {
        this.LOGO = LOGO;
    }

    public long getDELIVERY_FEE ()
    {
        return DELIVERY_FEE;
    }

    public void setDELIVERY_FEE (long DELIVERY_FEE)
    {
        this.DELIVERY_FEE = DELIVERY_FEE;
    }

    public String getBRANCH ()
    {
        return BRANCH;
    }

    public void setBRANCH (String BRANCH)
    {
        this.BRANCH = BRANCH;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [MENU = "+MENU+", NAME = "+NAME+", LOGO = "+LOGO+", DELIVERY_FEE = "+DELIVERY_FEE+", BRANCH = "+BRANCH+"]";
    }
}