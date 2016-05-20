package com.example.mymvp.listener;

import com.example.mymvp.bean.User;

/**
 * Created by Administrator on 2016/4/15.
 */
public interface onLoginListener {
    public void success(User user);
    public void onFail();
}
