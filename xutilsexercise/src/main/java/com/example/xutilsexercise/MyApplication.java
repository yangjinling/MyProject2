package com.example.xutilsexercise;

import android.app.Application;

import org.xutils.x;

/**
 * 作者：杨金玲 on 2016/4/25 10:12
 * 邮箱：18363820101@163.com
 */

public class MyApplication extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        //初始化XUtils3
        x.Ext.init(this);
        //设置debug模式
        x.Ext.setDebug(true);
    }
}
