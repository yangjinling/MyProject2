package com.example.frescoexercise;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * 作者：杨金玲 on 2016/4/22 10:35
 * 邮箱：18363820101@163.com
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        //初始化
        Fresco.initialize(getApplicationContext());

    }
}
