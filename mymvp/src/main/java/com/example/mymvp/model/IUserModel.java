package com.example.mymvp.model;

import com.example.mymvp.bean.User;
import com.example.mymvp.listener.onLoginListener;

/**
 * Created by Administrator on 2016/4/15.
 */
public class IUserModel implements UserModel {
    @Override
    public void load(final String name, final String password, final onLoginListener loginListener) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if ("123".equals(name)&&"456".equals(password)){
                    User user=new User();
                    user.setName(name);
                    user.setPassword(password);
                    loginListener.success(user);
                }else {
                    loginListener.onFail();
                }
            }
        }).start();
    }
}
