package com.cn.yinglian.mymaterialdesign;

import android.content.Context;
import android.support.v4.widget.NestedScrollView;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by Administrator on 2016/1/18.
 */
public class MyScrollview extends NestedScrollView  {
    // 滑动距离及坐标
      private float xDistance, yDistance, xLast, yLast;

    public MyScrollview(Context context) {
        super(context);
    }

    public MyScrollview(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyScrollview(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                xDistance = yDistance = 0f;
                               xLast = ev.getX();
                             yLast = ev.getY();

                break;
            case MotionEvent.ACTION_MOVE:

                final float curX = ev.getX();
                             final float curY = ev.getY();

                               xDistance += Math.abs(curX - xLast);
                             yDistance += Math.abs(curY - yLast);
                             xLast = curX;
                             yLast = curY;

                            if(xDistance > yDistance){
                                    return false;
                             }

                break;
        }

        return super.onInterceptTouchEvent(ev);
    }
}
