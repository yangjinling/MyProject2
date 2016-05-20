package com.example.mydefineview.view;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.mydefineview.R;

/**
 * 作者：杨金玲 on 2016/4/29 10:58
 * 邮箱：18363820101@163.com
 */

public class ZuHeView extends FrameLayout {
    private final Button back;
    private final TextView mTitle;

    public ZuHeView(final Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.title_item, this);
        back = ((Button) findViewById(R.id.back));
        mTitle = ((TextView) findViewById(R.id.title));
        back.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Activity) getContext()).finish();
            }
        });
    }

    public void setTitleText(String text) {
        mTitle.setText(text);
    }

    public void setLeftButtonText(String text) {
        back.setText(text);
    }

    public void setLeftButtonListener(OnClickListener l) {
        back.setOnClickListener(l);
    }

}
