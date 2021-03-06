package com.example.frescoexercise;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.frescoexercise.source.Creater;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.AbstractDraweeController;
import com.facebook.drawee.view.SimpleDraweeView;

public class MainActivity extends AppCompatActivity {

    private SimpleDraweeView view;
    private SimpleDraweeView view1;
    private SimpleDraweeView view2;
    private SimpleDraweeView view3;
    private SimpleDraweeView view4;
    private Creater mCreater;
    private AbstractDraweeController mController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    public void init() {
        view = ((SimpleDraweeView) findViewById(R.id.id_simple_drawee_view));
        view1 = ((SimpleDraweeView) findViewById(R.id.id_simple_drawee_view1));
        view2 = ((SimpleDraweeView) findViewById(R.id.id_simple_drawee_view2));
        view3 = ((SimpleDraweeView) findViewById(R.id.id_simple_drawee_view3));
        view4 = ((SimpleDraweeView) findViewById(R.id.id_simple_drawee_view4));
        mCreater = new Creater();
    }

    public void testDate() {
        //初始化HTTP的图片数据源
        mCreater.init(Creater.Type.NET);
        //显示一张HTTP图片
        view.setImageURI(Uri.parse(mCreater.getPic()));
        //显示一张HTTP图片保持一定宽高比例，如果4:3(1.33f)，注意xml里的写法
        view1.setImageURI(Uri.parse(mCreater.getPic()));
        view1.setAspectRatio(1.33f);
        //显示一张HTTP的GIF图片
        mController = Fresco.newDraweeControllerBuilder().setUri(Uri.parse(mCreater.getPic())).setAutoPlayAnimations(true).build();
        view2.setController(mController);

        //显示一张HTTP的图片，以圆形图片显示
        view3.setImageURI(Uri.parse(mCreater.getPic()));

        //显示一张HTTP的图片，以圆形带边框图片显示
        view4.setImageURI(Uri.parse(mCreater.getPic()));
    }
}
