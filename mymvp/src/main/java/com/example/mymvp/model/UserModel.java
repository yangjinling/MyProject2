package com.example.mymvp.model;

import com.example.mymvp.listener.onLoginListener;

/**
 * Created by Administrator on 2016/4/15.
 */
public interface UserModel {
    public void load(String name, String password, onLoginListener loginListener);
}
