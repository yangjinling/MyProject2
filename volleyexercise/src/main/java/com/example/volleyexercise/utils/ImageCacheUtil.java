package com.example.volleyexercise.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Environment;
import android.util.LruCache;

import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.ImageLoader;

import java.io.File;

/**
 * 作者：杨金玲 on 2016/4/22 16:58
 * 邮箱：18363820101@163.com
 */

public class ImageCacheUtil implements ImageLoader.ImageCache {

    private String TAG = ImageCacheUtil.this.getClass().getSimpleName();

    //缓存类
    private static LruCache<String, Bitmap> mLruCache;

    private static DiskBasedCache mDiskCaChe;
    //磁盘缓存大小
    private static final int DISKMAXSIZE = 10 * 1024 * 1024;

    public ImageCacheUtil(Context context) {
        int maxSize = (int) (Runtime.getRuntime().maxMemory() / 8);
        mLruCache=new LruCache<String,Bitmap>(maxSize){
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getRowBytes()*value.getHeight();
            }
        };
//        mDiskCaChe=DiskBasedCache.
    }

    @Override
    public Bitmap getBitmap(String url) {
      /*  if (mLruCache.get(url) != null) {
            // 从LruCache缓存中取
            Log.i(TAG,"从LruCahce获取");
            return mLruCache.get(url);
        } else {
            String key = MD5Utils.md5(url);
            try {
                if (mDiskLruCache.get(key) != null) {
                    // 从DiskLruCahce取
                    Snapshot snapshot = mDiskLruCache.get(key);
                    Bitmap bitmap = null;
                    if (snapshot != null) {
                        bitmap = BitmapFactory.decodeStream(snapshot.getInputStream(0));
                        // 存入LruCache缓存
                        mLruCache.put(url, bitmap);
                        Log.i(TAG,"从DiskLruCahce获取");
                    }
                    return bitmap;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }*/
        return null;

    }

    @Override
    public void putBitmap(String url, Bitmap bitmap) {
      /*  // 存入LruCache缓存
        mLruCache.put(url, bitmap);
        // 判断是否存在DiskLruCache缓存，若没有存入
        String key = MD5Utils.md5(url);
        try {
            if (mDiskLruCache.get(key) == null) {
                DiskLruCache.Editor editor = mDiskLruCache.edit(key);
                if (editor != null) {
                    OutputStream outputStream = editor.newOutputStream(0);
                    if (bitmap.compress(CompressFormat.JPEG, 100, outputStream)) {
                        editor.commit();
                    } else {
                        editor.abort();
                    }
                }
                mDiskLruCache.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
*/
    }

        /**
         * 该方法会判断当前sd卡是否存在，然后选择缓存地址
         *
         * @param context
         * @param uniqueName
         * @return
         */
        public static File getDiskCacheDir(Context context, String uniqueName) {
            String cachePath;
            if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState()) || !Environment.isExternalStorageRemovable()) {
                cachePath = context.getExternalCacheDir().getPath();
            } else {
                cachePath = context.getCacheDir().getPath();
            }
            return new File(cachePath + File.separator + uniqueName);
        }

        /**
         * 获取应用版本号
         *
         * @param context
         * @return
         */
        public int getAppVersion(Context context) {
            try {
                PackageInfo info = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
                return info.versionCode;
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
            return 1;
        }


}
