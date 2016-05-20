package com.example.rxjava_viewpager;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Scroller;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

public class MainActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener, View.OnTouchListener {
    @Bind(R.id.viewpager)
    ViewPager mViewPager;
    @Bind(R.id.viewGroup)
    ViewGroup mviewGroup;
    List<ImageView> mImageViews = new ArrayList<ImageView>();
    private ImageView[] mDotViews = null;
    private Subscription mSubscription;
    private int[] res = new int[]{R.mipmap.pic_1, R.mipmap.pic_2, R.mipmap.pic_3};
    private MyAdapter mAdapter;
    private Field mScrollerField;
    private ImageView imageView = null;
    private ViewPagerScroll mScroll;
    private int initPosition = 9999;
    private AtomicInteger what = new AtomicInteger(initPosition);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initViewPager();

    }

    @Override
    protected void onResume() {
        super.onResume();
        //轮播
        autoLoop();
    }

    //初始化viewpager
    public void initViewPager() {
        mAdapter = new MyAdapter(res);
        mViewPager.setAdapter(mAdapter);
        mDotViews = new ImageView[res.length];
        //小圈圈
        for (int i = 0; i < res.length; i++) {
            imageView = new ImageView(this);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            int dimension = (int) TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_DIP, 6, getResources()
                            .getDisplayMetrics());
            lp.setMargins(dimension, 0, dimension, 0);
            imageView.setLayoutParams(lp);
            mDotViews[i] = imageView;
            if (i == 0) {
                mDotViews[i]
                        .setBackgroundResource(R.drawable.banner_dian_focus);
            } else {
                mDotViews[i].setBackgroundResource(R.drawable.banner_dian_blur);
            }
            mviewGroup.addView(mDotViews[i]);
        }
        mViewPager.addOnPageChangeListener(this);
        mViewPager.setOnTouchListener(this);
        try {
            mScrollerField = ViewPager.class.getDeclaredField("mScroller");
            mScrollerField.setAccessible(true);
            mScroll = new ViewPagerScroll(mViewPager.getContext());
            mScrollerField.set(mViewPager, mScroll);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        mViewPager.setCurrentItem(initPosition);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        //取消订阅
        mSubscription.unsubscribe();
    }


    private void autoLoop() {
        if (mSubscription == null || mSubscription.isUnsubscribed()) {
            mSubscription = Observable.interval(3000, 3000, TimeUnit.MILLISECONDS)
                   /* .compose(this.<Long>bindToLifecycle())*/
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Action1<Long>() {
                        @Override
                        public void call(Long aLong) {
                            mScroll.setScrollDuration(1200);
                            int currentIndex = mViewPager.getCurrentItem();
                            Log.e("YJL", "currentIndex--->>" + currentIndex);
                            Log.e("YJL", "mAdapter.getCount()--->>>" + mAdapter.getCount());
                            if (++currentIndex == mAdapter.getCount()) {
                                mViewPager.setCurrentItem(0);
                            } else {
                                mViewPager.setCurrentItem(currentIndex, true);
                            }
                        }
                    });
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    /**
     * 根据Pager当前位置，获取计算后的实际位置
     *
     * @param position
     * @return
     */
    private int getNewPagerPosition(int position) {
        int newPosition = 0;
        if (position >= res.length) {
            newPosition = position % res.length;
        }
        if (position < 0) {
            newPosition = -position;
        }
        return newPosition;
    }

    @Override
    public void onPageSelected(int position) {
        Log.e("YJL", "position11111--->" + position);
        what.getAndSet(position);
        Log.e("YJL", "position22222--->" + position);
        int newPosition = getNewPagerPosition(position);
        Log.e("YJL", "newPosition--->>" + newPosition);
        for (int i = 0; i < res.length; i++) {
            Log.e("YJL", "i---->>" + i);
            mDotViews[newPosition]
                    .setBackgroundResource(R.drawable.banner_dian_focus);
            if (newPosition != i) {
                mDotViews[i]
                        .setBackgroundResource(R.drawable.banner_dian_blur);
            }
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    private boolean isContinue = true;

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (v.getId() == R.id.viewpager) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                case MotionEvent.ACTION_MOVE:
                    isContinue = false;
                    break;
                case MotionEvent.ACTION_UP:
                    isContinue = true;
                    /** 手指滑动的时候，加快切换速度 */
                    mScroll.setScrollDuration(100);
                    break;
                default:
                    isContinue = true;
                    break;
            }
            return false;
        }
        return false;
    }


    public class MyAdapter extends PagerAdapter {
        private int[] mdatas;

        public MyAdapter(int[] datas) {
            this.mdatas = datas;
        }

        @Override
        public int getCount() {
            return Integer.MAX_VALUE;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            int index = position % mdatas.length;
            ImageView iv;
            if (mImageViews.size() > 0) {
                iv = mImageViews.remove(0);
            } else {
                iv = new ImageView(getApplicationContext());
                iv.setLayoutParams(new ViewPager.LayoutParams());
                iv.setScaleType(ImageView.ScaleType.CENTER_CROP);
            }
            iv.setImageResource(mdatas[index]);
            container.addView(iv);
            return iv;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
            mImageViews.add((ImageView) object);
        }
    }

    //自定义ScrollListener
    public class ViewPagerScroll extends Scroller {
        private int mScrollDuration = 1200;// 滑动速度

        public int getScrollDuration() {
            return mScrollDuration;
        }

        public void setScrollDuration(int scrollDuration) {
            mScrollDuration = scrollDuration;
        }

        public ViewPagerScroll(Context context) {
            super(context);
        }

        public ViewPagerScroll(Context context, Interpolator interpolator) {
            super(context, interpolator);
        }

        public ViewPagerScroll(Context context, Interpolator interpolator, boolean flywheel) {
            super(context, interpolator, flywheel);
        }

        @Override
        public void startScroll(int startX, int startY, int dx, int dy) {
            super.startScroll(startX, startY, dx, mScrollDuration);
        }

        @Override
        public void startScroll(int startX, int startY, int dx, int dy, int duration) {
            super.startScroll(startX, startY, dx, dy, mScrollDuration);
        }
    }
}
