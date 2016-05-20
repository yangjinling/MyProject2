package com.example.mymvp.view;

import com.example.mymvp.bean.User;

/**
 * Created by Administrator on 2016/4/15.
 */
public interface LoginView {
    String getName();

    String getPass();

    void clearName();

    void clearPass();

    void toActivity(User user);

    void showLoging();

    void hindLoging();

    void showFailedError();

}
