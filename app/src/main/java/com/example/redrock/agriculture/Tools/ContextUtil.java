package com.example.redrock.agriculture.Tools;

import android.app.Application;

/**
 * Created by REDROCK on 12/9/2015.
 */
public class ContextUtil extends Application{
    private static ContextUtil instance;
    public static ContextUtil getInstance(){
        return instance;
    }

    @Override
    public void onCreate(){
        super.onCreate();
        instance=this;
    }
}
