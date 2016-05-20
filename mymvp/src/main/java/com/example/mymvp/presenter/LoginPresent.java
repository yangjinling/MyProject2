package com.example.mymvp.presenter;

import android.os.Handler;

import com.example.mymvp.bean.User;
import com.example.mymvp.listener.onLoginListener;
import com.example.mymvp.model.IUserModel;
import com.example.mymvp.view.LoginView;


/**
 * Created by Administrator on 2016/4/15.
 */
public class LoginPresent {
    private IUserModel mIUserModel;
    private LoginView mLoginView;
    private Handler mHandler = new Handler();

    public LoginPresent(LoginView loginView) {
        this.mIUserModel = new IUserModel();
        mLoginView = loginView;
    }

    public void login() {
        mLoginView.showLoging();
        mIUserModel.load(mLoginView.getName(), mLoginView.getPass(), new onLoginListener() {
            @Override
            public void success(final User user) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        mLoginView.hindLoging();
                        mLoginView.toActivity(user);
                    }
                });

            }

            @Override
            public void onFail() {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        mLoginView.hindLoging();
                        mLoginView.showFailedError();
                    }
                });

            }
        });
    }

    public void clear() {
        mLoginView.clearName();
        mLoginView.clearPass();
    }
}
