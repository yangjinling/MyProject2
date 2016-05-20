package com.example.rxjava_viewpager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.rxjava_viewpager.bean.GetModel;
import com.example.rxjava_viewpager.bean.PostModel;
import com.example.rxjava_viewpager.bean.PutModel;
import com.example.rxjava_viewpager.util.NetUtil;

import java.util.TreeMap;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

public class NetActivity extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.get)
    Button mGet;
    @Bind(R.id.post)
    Button mPost;
    @Bind(R.id.put)
    Button mPut;
    @Bind(R.id.tv)
    TextView tv;
    private NetUtil mNetUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_net);
        ButterKnife.bind(this);
        mGet.setOnClickListener(this);
        mPost.setOnClickListener(this);
        mPut.setOnClickListener(this);
        mNetUtil = new NetUtil();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.get:
                getDate();
                break;
            case R.id.post:
                postDate();
                break;
            case  R.id.put:
                putData();
                break;
        }
    }

    private void getDate(){
        mNetUtil.getText().observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Action1<GetModel>() {
            @Override
            public void call(GetModel getModel) {
                tv.setText("Get Result\r\n"+getModel);
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                tv.setText("GetResult\r\n"+throwable.getMessage());
            }
        });
    }

    private void postDate(){
        TreeMap<String,Object> map=new TreeMap<>();
        mNetUtil.postText(map).observeOn(AndroidSchedulers.mainThread())
          .subscribe(new Action1<PostModel>() {
              @Override
              public void call(PostModel postModel) {
                  tv.setText("Get Result\r\n"+postModel);
              }
          }, new Action1<Throwable>() {
              @Override
              public void call(Throwable throwable) {
                  tv.setText("Get Result\r\n"+throwable.getMessage());
              }
          });
    }

    public void putData(){
        TreeMap<String,Object>map=new TreeMap<>();
        mNetUtil.putText(map).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<PutModel>() {
                    @Override
                    public void call(PutModel putModel) {
                        tv.setText("Get Result"+putModel);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        tv.setText(throwable.getMessage());
                    }
                });
    }
}
