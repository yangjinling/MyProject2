package com.example.rxjava_viewpager;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.concurrent.TimeUnit;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

public class TimerActivity extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.show)
    Button show;
    @Bind(R.id.imv)
    ImageView imv;

    private Subscription mSubscription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);
        ButterKnife.bind(this);
        show.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.show:
                startTimer();
                break;
        }
    }

    public void startTimer() {
        mSubscription = Observable.timer(3000, TimeUnit.MILLISECONDS)
                /*.compose()*/
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Long>() {
                    @Override
                    public void call(Long aLong) {
                        imv.setVisibility(View.VISIBLE);
                        ObjectAnimator.ofFloat(imv, "alpha", 0.0f, 1.0f)
                                .setDuration(800).start();
                    }
                });
    }
}
