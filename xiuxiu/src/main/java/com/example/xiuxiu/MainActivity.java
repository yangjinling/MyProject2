package com.example.xiuxiu;

import android.graphics.Color;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.DecelerateInterpolator;

public class MainActivity extends AppCompatActivity {
    // 声明一个SoundPool
    private SoundPool sp;
    // 定义一个整型用load（）；来设置suondIDf
    private int music;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sp = new SoundPool(10, AudioManager.STREAM_SYSTEM, 5);
        music=sp.load(this,R.raw.hongbao_gq,1);
        final RippleLayout rippleLayout = (RippleLayout) findViewById(R.id.rippleLayout);
             rippleLayout.post(new Runnable() {
                      public void run() {
                             rippleLayout.init(rippleLayout.getWidth() / 2,//中心点x
                                          rippleLayout.getHeight() / 2,//中心点y
                                                45,//波纹的初始半径
                                          /*   Math.min(rippleLayout.getWidth(), rippleLayout.getHeight()) / 2 + 100*/
                                     150,//波纹的结束半径
                                                900,//duration
                                              Color.GREEN,//颜色..
                                           9,//圆圈宽度
                                              new DecelerateInterpolator());//开始快,后来慢
                           }
                 });

             findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
                       public void onClick(View v) {
                              rippleLayout.doRipple();
                                sp.play(music,1,1,0,0,1);
                           }
                 });

           }

}
