package com.example.rxjava_viewpager;

import android.os.Bundle;

import com.trello.rxlifecycle.components.RxFragment;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

/**
 * Created by Administrator on 2016/4/18.
 */
public class Fragment extends RxFragment {

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }
    public  void create(){
        Observable.interval(3000,3000, TimeUnit.MILLISECONDS)
                .compose(this.<Long>bindToLifecycle())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Long>() {
                    @Override
                    public void call(Long aLong) {

                    }
                });
    }
}
