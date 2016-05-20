package com.example.rxjava_viewpager.util;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.squareup.okhttp.Request;

import java.util.TreeMap;

import rx.Observable;
import rx.Subscriber;

/**
 * 作者：杨金玲 on 2016/4/19 08:54
 * 邮箱：18363820101@163.com
 */

public abstract class BaseUtil {
    private static final Gson mGson;

    static {
        mGson = new Gson();
    }

    protected <T> Observable<T> creatObservable(final String url, final String method, final TreeMap<String, Object> params, final Class<T> mclass) {
        return Observable.create(new Observable.OnSubscribe<T>() {
            @Override
            public void call(Subscriber<? super T> subscriber) {
                Request request = XgoHttpClient.getClient().getRequest(url, method, params);
                String json = XgoHttpClient.getClient().execute2String(request);
                sendDate(subscriber,json,mclass);
            }
        });
    }

    protected <T> void  sendDate(Subscriber<? super T> subscriber,String json,Class<T>mclass){
        if (TextUtils.isEmpty(json)){
            subscriber.onError(new Throwable("no data"));
            subscriber.onCompleted();
            return;
        }else {
            T data= mGson.fromJson(json,mclass);
            subscriber.onNext(data);
            subscriber.onCompleted();
        }
    }
}
