package com.frank.progressglide;

import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.lang.ref.WeakReference;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private ImageView iv_0;
    private ProgressBar progressBar;
    private TextView progressTextView;

    private final MainActivityHandler mainActivityHandler = new MainActivityHandler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iv_0 = (ImageView) findViewById(R.id.iv_0);
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        progressTextView = (TextView) findViewById(R.id.progress_text_view);
        //960 × 533 pixels,25059 bytes
        Log.e("YJL",""+ Environment.getExternalStorageDirectory().getPath());
        Glide.with(this)
             /*   .using(new ProgressModelLoader(mainActivityHandler))*/
                .load("android.resource://com.frank.progressglide/drawable/" + R.drawable.ic_launcher)
                .into(iv_0);
    }

    private static class MainActivityHandler extends Handler {
        private final WeakReference<MainActivity> mActivity;

        public MainActivityHandler(MainActivity activity) {
            mActivity = new WeakReference<MainActivity>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            final MainActivity activity = mActivity.get();
            if (activity != null) {
                switch (msg.what) {
                    case 1:
                        //int bytesRead = msg.arg1;
                        //int contentLength = msg.arg2;
                        int percent = msg.arg1 * 100 / msg.arg2;
                        activity.progressBar.setProgress(percent);
                        activity.progressTextView.setText("loading..." + percent + "%");
                        if (msg.arg1 == msg.arg2) {
                            activity.progressTextView.setText(msg.arg2 + " bytes");
                        }
                        break;
                    default:
                        break;
                }
            }
        }
    }

}

