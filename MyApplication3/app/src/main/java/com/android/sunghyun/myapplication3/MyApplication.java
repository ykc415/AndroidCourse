package com.android.sunghyun.myapplication3;

import android.app.Application;

/**
 * Created by YKC on 2016. 11. 21..
 */

public class MyApplication extends Application {

    private static boolean firstService = false;

    public static void setServiceStatus(boolean flag) {
        firstService = flag;
    }

    public static boolean isFirstService() {
        return firstService;
    }

}
