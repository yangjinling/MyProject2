package com.cn.yinglian.mymaterialdesign;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Administrator on 2016/1/18.
 */
public class MyViewPagerFragment extends Fragment {

    private TextView textview;
    private int pager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragmentlayout,container,false);
        textview = ((TextView) view.findViewById(R.id.textview));
        pager = getArguments().getInt("PAGER");
        switch (pager){
            case 1:
                textview.setText("第"+pager+"个页面");
                break;
            case 2:
                textview.setText("第"+pager+"个页面");
                break;
            case 3:
                textview.setText("第"+pager+"个页面");
                break;
            case 4:
                textview.setText("第"+pager+"个页面");
                break;
            case  5:
                textview.setText("第"+pager+"个页面");
                break;
        }

        return view;
    }
}
