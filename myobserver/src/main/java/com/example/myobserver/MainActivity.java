package com.example.myobserver;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.myobserver.bean.Content;
import com.example.myobserver.observe.ContentObserved;
import com.example.myobserver.observe.IContentObserved;
import com.example.myobserver.subject.ContentObserve;

public class MainActivity extends AppCompatActivity implements ContentObserve, View.OnClickListener {
    Handler mHandler=new Handler();
    private TextView tv;
    private Button change;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = ((TextView) findViewById(R.id.tv));
        change = ((Button) findViewById(R.id.change));
        change.setOnClickListener(this);
    }

    public void obsever() {
        //实例化一个被观察者
        final ContentObserved observed=new IContentObserved();
        //添加观察者
        observed.add(this);
        final Content content=new Content();
        content.setContent("你好");
        //通知观察值
        observed.notifyChange(content);

        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Content content1=new Content();
                content1.setContent("不好");
                observed.notifyChange(content1);
            }
        },2000);

    }

    @Override
    public void update(Content content) {
        tv.setText(""+content.getContent());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.change:
                obsever();
                break;
        }
    }
}
