package com.example.myobserver.observe;

import com.example.myobserver.bean.Content;
import com.example.myobserver.subject.ContentObserve;

/**
 * Created by Administrator on 2016/4/15.
 */
public interface ContentObserved {
     void add(ContentObserve contentObserve);
     void remove(ContentObserve contentObserve);
    void notifyChange(Content content);
}
