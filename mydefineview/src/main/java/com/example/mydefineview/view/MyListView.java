package com.example.mydefineview.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.example.mydefineview.R;

/**
 * 作者：杨金玲 on 2016/4/29 11:16
 * 邮箱：18363820101@163.com
 */

public class MyListView extends ListView implements GestureDetector.OnGestureListener, View.OnTouchListener {
    private final GestureDetector mGestureDetector;
    private View mDeleteButton;
    private OnDeleteListener mOnDeleteListener;
    private int selectedItem;
    private ViewGroup itemLayout;
    private boolean isDeleteShow;

    public interface OnDeleteListener {
        void onDelete(int position);
    }

    public MyListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mGestureDetector = new GestureDetector(getContext(), this);
        setOnTouchListener(this);
    }

    public void setOnDeleteListener(OnDeleteListener onDeleteListener) {
        mOnDeleteListener = onDeleteListener;
    }

    @Override
    public boolean onDown(MotionEvent e) {
        if (!isDeleteShow) {
            selectedItem = pointToPosition((int) e.getX(), (int) e.getY());
        }
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        if (!isDeleteShow && Math.abs(velocityX) > Math.abs(velocityY)) {
            mDeleteButton = LayoutInflater.from(getContext()).inflate(R.layout.delete_button, null);
            mDeleteButton.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemLayout.removeView(mDeleteButton);
                    mDeleteButton = null;
                    isDeleteShow = false;
                    mOnDeleteListener.onDelete(selectedItem);
                }
            });
            itemLayout = (ViewGroup) getChildAt(selectedItem - getFirstVisiblePosition());
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            params.addRule(RelativeLayout.CENTER_VERTICAL);
            itemLayout.addView(mDeleteButton, params);
            isDeleteShow = true;
        }
        return false;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (isDeleteShow) {
            itemLayout.removeView(mDeleteButton);
            mDeleteButton = null;
            isDeleteShow = false;
            return false;
        } else {
            return mGestureDetector.onTouchEvent(event);
        }
    }
}
