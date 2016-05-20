package com.example.mydefineview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

/**
 * 作者：杨金玲 on 2016/4/29 10:33
 * 邮箱：18363820101@163.com
 */

public class DaoJiShiView extends View implements View.OnClickListener{
    private final Rect mRect;
    private Paint mPaint;
    private int mCount;
    public DaoJiShiView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint();
        mRect = new Rect();
        setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        mCount++;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setColor(Color.BLUE);
        canvas.drawRect(0,0,getWidth(),getHeight(),mPaint);
        mPaint.setColor(Color.YELLOW);
        mPaint.setTextSize(20);
        String text=String.valueOf(mCount);
        mPaint.getTextBounds(text,0,text.length(),mRect);//获取字体的大小
        int width=mRect.width();
        int height=mRect.height();
        canvas.drawText(text,getWidth()/2-width/2,getHeight()/2+height/2,mPaint);

    }
}
