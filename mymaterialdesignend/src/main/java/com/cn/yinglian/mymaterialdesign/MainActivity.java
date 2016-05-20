package com.cn.yinglian.mymaterialdesign;

import android.graphics.Color;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private FloatingActionButton snackbar;
    private ListView lsits;
    private TabLayout tablayout;
    private ViewPager viewpager;
    private List<MyViewPagerFragment>frgments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        snackbar = ((FloatingActionButton) findViewById(R.id.snackbar));
//        snackbar.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Snackbar.make(v,"FAB",Snackbar.LENGTH_SHORT)
//                        .setAction("cancle", new View.OnClickListener() {
//                            @Override
//                            public void onClick(View v) {
//                                Toast.makeText(MainActivity.this,"sss",Toast.LENGTH_SHORT).show();
//                            }
//                        })
//                        .show();
//            }
//        });

        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        //使用CollapsingToolbarLayout必须把title设置到CollapsingToolbarLayout上，设置到Toolbar上则不会显示
        CollapsingToolbarLayout mCollapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar_layout);
        mCollapsingToolbarLayout.setTitle("CollapsingToolbarLayout");
        //通过CollapsingToolbarLayout修改字体颜色
        mCollapsingToolbarLayout.setExpandedTitleColor(Color.WHITE);//设置还没收缩时状态下字体颜色
        mCollapsingToolbarLayout.setCollapsedTitleTextColor(Color.GREEN);//设置收缩后Toolbar上字体的颜色


        snackbar = ((FloatingActionButton) findViewById(R.id.snackbar));
        snackbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "FAB", Snackbar.LENGTH_SHORT)
                        .setAction("cancle", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(MainActivity.this, "sss", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .show();
            }
        });

//
        lsits = ((ListView) findViewById(R.id.listview));
        ArrayList<String>lists=new ArrayList<String>();
        lists.add("1");
        lists.add("2");
        lists.add("3");
        lists.add("4");
        lists.add("5");
        lists.add("6");
        lists.add("7");
        lists.add("8");
        lists.add("9");
        lists.add("10");
        lists.add("11");
        lists.add("12");
        ArrayAdapter<String>adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,lists);
        lsits.setAdapter(adapter);

//        tablayout = ((TabLayout) findViewById(R.id.tablelayout));
//        viewpager = ((MyViewPager) findViewById(R.id.viewpager));
//        frgments=new ArrayList<MyViewPagerFragment>();
//        for (int i=0;i<5;i++){
//            MyViewPagerFragment fragment=new MyViewPagerFragment();
//            Bundle bundle=new Bundle();
//            bundle.putInt("PAGER",i+1);
//            fragment.setArguments(bundle);
//            frgments.add(fragment);
//        }
//        MyAdapter adapter=new MyAdapter(getSupportFragmentManager(),frgments);
//        viewpager.setAdapter(adapter);
//        tablayout.setupWithViewPager(viewpager);
//        tablayout.setTabGravity(TabLayout.GRAVITY_FILL);

    }
}
