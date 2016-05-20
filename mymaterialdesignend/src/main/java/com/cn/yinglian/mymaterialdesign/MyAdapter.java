package com.cn.yinglian.mymaterialdesign;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by Administrator on 2016/1/18.
 */
public class MyAdapter extends FragmentPagerAdapter {
    private List<MyViewPagerFragment>fragments;
    String title[]=new String[]{"TAB1","TAB2","TAB3","TAB4","TAB5"};
    public MyAdapter(FragmentManager fm,List<MyViewPagerFragment>fragments) {
        super(fm);
        this.fragments=fragments;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return title[position];
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size()==0?0:fragments.size();
    }
}
