package com.zhy.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.zhy.base.adapter.ViewHolder;
import com.zhy.base.adapter.recyclerview.CommonAdapter;
import com.zhy.base.adapter.recyclerview.DividerItemDecoration;
import com.zhy.base.adapter.recyclerview.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private List<String> mDatas = new ArrayList<>();
    private LinearLayoutManager mLinearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview);

        initDatas();

        mRecyclerView = (RecyclerView) findViewById(R.id.id_recyclerview);
        mLinearLayoutManager = new LinearLayoutManager(this);
        mLinearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));

        final CommonAdapter<String> adapter = new CommonAdapter<String>(this, R.layout.item_list, mDatas) {
            @Override
            public void convert(ViewHolder holder, String s) {
                holder.setText(R.id.id_item_list_title, s);
            }
        };
        mRecyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(ViewGroup parent, View view, Object o, int position) {
                Toast.makeText(RecyclerViewActivity.this, "点击了第" + position + "的位置", Toast.LENGTH_SHORT).show();
            }

            @Override
            public boolean onItemLongClick(ViewGroup parent, View view, Object o, int position) {
                Toast.makeText(RecyclerViewActivity.this, "长按了第" + position + "的位置", Toast.LENGTH_SHORT).show();
                mDatas.remove(position);
                adapter.notifyItemRemoved(position);
                return false;
            }
        });
    }

    private void initDatas() {
        for (int i = 'A'; i < 'z'; i++) {
            mDatas.add((char) i + "");
        }
    }
}
