package com.example.fenleiexercise;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final String[] mMenus = {"常用分类", "服饰内衣", "鞋靴", "手机",
            "家用电器", "数码", "电脑办公", "个护化妆", "图书"};

    private MyListView listview1;
    private MyListView listview2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listview1 = ((MyListView) findViewById(R.id.list1));
        listview2 = ((MyListView) findViewById(R.id.list2));
        listview1.setAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, mMenus));

        listview1.setOnItemClickListener(new ItemClick1());
        listview2.setOnItemClickListener(new ItemClick2());

    }

    private class ItemClick1 implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position,
                                long id) {
            listview1.smoothScrollToPositionFromTop(position, 0);
            String[] items = new String[(position + 1) * 2];
            for (int i = 0; i < items.length; i++) {
                items[i] = mMenus[position] + "中的数据：" + i;
            }
            listview2.setAdapter(new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, items));

        }
    }

    private class ItemClick2 implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position,
                                long id) {
            listview1.smoothScrollToPositionFromTop(position, 0);
            String[] items = new String[(position + 1) * 2];
            for (int i = 0; i < items.length; i++) {
                items[i] = mMenus[position] + "中的数据：" + i;
            }
            listview2.setAdapter(new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, items));
            listview2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Toast.makeText(MainActivity.this, "click" + position + "的数据", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }


}
