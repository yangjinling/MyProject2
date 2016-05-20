package com.example.myobserver.observe;

import com.example.myobserver.bean.Content;
import com.example.myobserver.subject.ContentObserve;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/4/15.
 */
public class IContentObserved implements ContentObserved {

    private List<ContentObserve> mContentObserves = new ArrayList<>();

    @Override
    public void add(ContentObserve contentObserve) {
        mContentObserves.add(contentObserve);
    }

    @Override
    public void remove(ContentObserve contentObserve) {
        mContentObserves.remove(contentObserve);
    }

    @Override
    public void notifyChange(Content content) {
        for (ContentObserve contentObserve : mContentObserves) {
            contentObserve.update(content);
        }
    }
}
