package com.example.myviewpager;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private MyJazzyViewPager mviewpager;
    private int[] imgrc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mviewpager = ((MyJazzyViewPager) findViewById(R.id.viewpager));
        imgrc = new int[]{R.mipmap.a, R.mipmap.b, R.mipmap.c, R.mipmap.d};
        mviewpager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return imgrc.length;
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                ImageView imageView=new ImageView(MainActivity.this);
                imageView.setBackgroundResource(imgrc[position]);
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                container.addView(imageView);
                mviewpager.setObjectForPosition(imageView,position);
                return imageView;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView((View) object);
            }
        });
    }
}
