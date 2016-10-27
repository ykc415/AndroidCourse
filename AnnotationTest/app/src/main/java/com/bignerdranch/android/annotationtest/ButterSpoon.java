package com.bignerdranch.android.annotationtest;

import android.app.Activity;
import android.widget.Button;

import java.lang.reflect.Field;

/**
 * Created by YKC on 2016. 10. 27..
 */

public class ButterSpoon {
    public static void bind(Activity activity) {
        try {
            for(Field field : activity.getClassLoader().loadClass("MainActivity").getDeclaredFields()) {
                if(field.isAnnotationPresent(com.bignerdranch.android.annotationtest.BindView.class)){
                    BindView bindView = field.getAnnotation(BindView.class);
                    int viewID = bindView.viewID();
                    Button
                    method = (Button)activity.findViewById(viewID);
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
