package com.example.my;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.util.Hashtable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button generate;
    private ImageView imv;
    private int QR_WIDTH;
    private int QR_HEIGHT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        generate = ((Button) findViewById(R.id.generate_qrcode));
        imv = ((ImageView) findViewById(R.id.iv));
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int width = metrics.widthPixels;     // 屏幕宽度（像素）
        int height = metrics.heightPixels;   // 屏幕高度（像素）
        float density = metrics.density;      // 屏幕密度（0.75 / 1.0 / 1.5）
        int densityDpi = metrics.densityDpi;  // 屏幕密度DPI（120 / 160 / 240)
    /*    QR_HEIGHT=imv.getMeasuredHeight();
        QR_WIDTH=imv.getMeasuredWidth();*/
        Log.e("YJL","height---->>>"+height);
        Log.e("YJL","width---->>>"+width);
        QR_HEIGHT=height;
        QR_WIDTH=width;
        generate.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.generate_qrcode:
                createQRImageView("http://www.cnblogs.com/mythou/");
                break;
        }
    }


    private void createQRImageView(String url) {
        if ("".equals(url) || url == null || url.length() < 1) {
            return;
        } else {
            try {
                Hashtable<EncodeHintType, String> hints = new Hashtable<EncodeHintType, String>();
                hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
                //图像数据转换，使用了矩阵转换
                BitMatrix bitMatrix = null;

                bitMatrix = new QRCodeWriter().encode(url, BarcodeFormat.QR_CODE, QR_WIDTH, QR_HEIGHT, hints);

                int[] pixels = new int[QR_WIDTH * QR_HEIGHT];
                //下面这里按照二维码的算法，逐个生成二维码的图片，
                //两个for循环是图片横列扫描的结果
                for (int y = 0; y < QR_HEIGHT; y++) {
                    for (int x = 0; x < QR_WIDTH; x++) {
                        if (bitMatrix.get(x, y)) {
                            pixels[y * QR_WIDTH + x] = 0xff000000;
                        } else {
                            pixels[y * QR_WIDTH + x] = 0xffffffff;
                        }
                    }
                }
                //生成二维码图片的格式，使用ARGB_8888
                Bitmap bitmap = Bitmap.createBitmap(QR_WIDTH, QR_HEIGHT, Bitmap.Config.ARGB_8888);
                bitmap.setPixels(pixels, 0, QR_WIDTH, 0, 0, QR_WIDTH, QR_HEIGHT);
                //显示到一个ImageView上面
                imv.setImageBitmap(bitmap);
            } catch (WriterException e) {
                e.printStackTrace();
            }
        }

    }
}
