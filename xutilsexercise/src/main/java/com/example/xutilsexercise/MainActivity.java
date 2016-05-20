package com.example.xutilsexercise;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.xutilsexercise.bean.PersonInfoBean;
import com.example.xutilsexercise.callBack.MyCallBack;
import com.example.xutilsexercise.callBack.MyProgressCallBack;
import com.example.xutilsexercise.util.XUtil;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.io.File;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Yan
 */
@ContentView(R.layout.activity_main)
public class MainActivity extends ActionBarActivity {
    @ViewInject(R.id.btn_fragment)
    Button btn_fragment;
    @ViewInject(R.id.btn_get)
    Button btn_get;
    @ViewInject(R.id.btn_post)
    Button btn_post;
    @ViewInject(R.id.btn_uploadfile)
    Button btn_uploadfile;
    @ViewInject(R.id.btn_downloadfile)
    Button btn_downloadfile;
    @ViewInject(R.id.btn_downloadprogressfile)
    Button btn_downloadprogressfile;
    @ViewInject(R.id.btn_getxml)
    Button btn_getxml;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    PersonInfoBean bean = (PersonInfoBean) msg.obj;
                    Toast.makeText(MainActivity.this,""+bean.toString(),Toast.LENGTH_SHORT).show();
                    Log.e("YJL", bean.toString());
                    break;
                case 2:
                    break;
                case 3:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);

        btn_fragment.setText("fragment注解演示");
        btn_get.setText("发送get请求");
        btn_post.setText("发送post请求");
        btn_uploadfile.setText("上传文件");
        btn_downloadfile.setText("下载文件");
        btn_downloadprogressfile.setText("下载文件(带进度条)");
        btn_getxml.setText("发送get请求(服务器返回xml格式)");
    }

    //等同于@Event(value={R.id.btn_get,R.id.btn_post},type = View.OnClickListener.class)
    @Event(value = {R.id.btn_get, R.id.btn_post, R.id.btn_fragment, R.id.btn_uploadfile, R.id.btn_downloadfile, R.id.btn_downloadprogressfile, R.id.btn_getxml})
    private void getEvent(View view) {
        switch (view.getId()) {
            case R.id.btn_get:
                Toast.makeText(MainActivity.this, btn_get.getText().toString().trim(), Toast.LENGTH_SHORT).show();
                get();
                break;
            case R.id.btn_post:
                Toast.makeText(MainActivity.this, btn_post.getText().toString().trim(), Toast.LENGTH_SHORT).show();
                post();
                break;
            case R.id.btn_fragment:
                Intent intent = new Intent(MainActivity.this, FragmentInjectActvity.class);
                startActivity(intent);
                break;
            case R.id.btn_uploadfile:
                uploadfile();
                break;
            case R.id.btn_downloadfile:
                downloadfile();
                break;
            case R.id.btn_downloadprogressfile:
                downloadprogressfile();
                break;
            case R.id.btn_getxml:
                getxml();
                break;
        }
    }

    private void getxml() {
        String url = "http://flash.weather.com.cn/wmaps/xml/china.xml";
        XUtil.Get(url, null, new MyCallBack<String>(){

            @Override
            public void onSuccess(String xmlString) {
                super.onSuccess(xmlString);
                try{
                    XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
                    XmlPullParser xmlPullParser = factory.newPullParser();
                    xmlPullParser.setInput(new StringReader(xmlString));
                    int eventType = xmlPullParser.getEventType();
                    while (eventType != XmlPullParser.END_DOCUMENT) {
                        switch (eventType) {
                            case XmlPullParser.START_TAG:
                                String nodeName = xmlPullParser.getName();
                                if ("city".equals(nodeName)) {
                                    String pName = xmlPullParser.getAttributeValue(0);
                                    Log.e("TAG", "city is " + pName);
                                }
                                break;
                        }
                        eventType = xmlPullParser.next();
                    }
                }catch(Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                super.onError(ex, isOnCallback);
            }

        });
    }

    private void downloadprogressfile() {
        //文件下载地址
        String url = "";
        //文件保存在本地的路径
        String filepath = "";
        XUtil.DownLoadFile(url, filepath,new MyProgressCallBack<File>(){

            @Override
            public void onSuccess(File result) {
                super.onSuccess(result);

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                super.onError(ex, isOnCallback);

            }

            @Override
            public void onLoading(long total, long current, boolean isDownloading) {
                super.onLoading(total, current, isDownloading);
            }
        });
    }

    private void downloadfile() {
        //文件下载地址
        String url = "";
        //文件保存在本地的路径
        String filepath = "";
        XUtil.DownLoadFile(url, filepath,new MyCallBack<File>(){
            @Override
            public void onSuccess(File result) {
                super.onSuccess(result);

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                super.onError(ex, isOnCallback);

            }

        });
    }

    /**
     * 上传文件(支持多文件上传)
     */
    private void uploadfile() {
        //图片上传地址
        String url = "";
        Map<String, Object> map = new HashMap<>();
        //传入自己的相应参数
        //map.put(key, value);
        //map.put(key, value);
        XUtil.UpLoadFile(url, map, new MyCallBack<String>(){

            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                super.onError(ex, isOnCallback);
            }

        });

    }

    private void get() {
        String url = "http://api.k780.com:88/?app=idcard.get";
        Map<String, String> map = new HashMap<>();
        map.put("appkey", "10003");
        map.put("sign", "b59bc3ef6191eb9f747dd4e83c99f2a4");
        map.put("format", "json");
        map.put("idcard", "110101199001011114");
        XUtil.Get(url, map, new MyCallBack<PersonInfoBean>(){

            @Override
            public void onSuccess(PersonInfoBean result) {
                super.onSuccess(result);
                Log.e("result", result.toString());
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                super.onError(ex, isOnCallback);


            }

        });
    }

    private void post() {
        String url = "http://api.k780.com:88/?app=idcard.get";
        Map<String, Object> map = new HashMap<>();
        map.put("appkey", "10003");
        map.put("sign", "b59bc3ef6191eb9f747dd4e83c99f2a4");
        map.put("format", "json");
        map.put("idcard", "110101199001011114");
        XUtil.Post(url, map, new MyCallBack<PersonInfoBean>(){

            @Override
            public void onSuccess(PersonInfoBean result) {
                super.onSuccess(result);
                Log.e("result", result.toString());
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                super.onError(ex, isOnCallback);

            }
        });
    }
}
