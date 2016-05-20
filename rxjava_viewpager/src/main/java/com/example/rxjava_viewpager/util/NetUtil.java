package com.example.rxjava_viewpager.util;

import com.example.rxjava_viewpager.bean.GetModel;
import com.example.rxjava_viewpager.bean.PostModel;
import com.example.rxjava_viewpager.bean.PutModel;

import java.util.TreeMap;

import rx.Observable;

/**
 * 作者：杨金玲 on 2016/4/19 08:51
 * 邮箱：18363820101@163.com
 */

public class NetUtil extends BaseUtil{
    private static final String BaseUrl="http://service.test.xgo.com.cn:8080/app/v1/demo/";
    /**
     * get请求
     * */

    public Observable<GetModel> getText(){
        String path="1";
        return creatObservable(BaseUrl+path,XgoHttpClient.METHOD_GET ,null,GetModel.class);
    }

    /**
     * POS请求
     * */
    public Observable<PostModel> postText(TreeMap<String,Object>params){
        return creatObservable(BaseUrl,XgoHttpClient.METHOD_POST,params,PostModel.class);
    }


    /***
     * PUT请求
     */
    public Observable<PutModel>putText(TreeMap<String,Object> params){
        return creatObservable(BaseUrl,XgoHttpClient.METHOD_PUT,params,PutModel.class);
    }
}
