package com.example.myobserver.subject;

import android.util.Log;

import com.example.myobserver.bean.Content;

/**
 * Created by Administrator on 2016/4/15.
 */
public class IContentObserve implements ContentObserve {
    @Override
    public void update(Content content) {
        Log.e("YJL", "" + content.getContent());
    }
}
