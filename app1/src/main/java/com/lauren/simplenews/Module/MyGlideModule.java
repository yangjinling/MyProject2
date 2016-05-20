package com.lauren.simplenews.Module;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.load.engine.cache.DiskLruCacheFactory;
import com.bumptech.glide.module.GlideModule;

/**
 * 作者：杨金玲 on 2016/5/3 13:40
 * 邮箱：18363820101@163.com
 */

public class MyGlideModule implements GlideModule {

    @Override
    public void applyOptions(Context context, GlideBuilder builder) {
       int cacheSize100MegaBytes = 104857600;
        /* builder.setDiskCache(new InternalCacheDiskCacheFactory(context, cacheSize100MegaBytes));*/
       /* builder.setDiskCache(
                new ExternalCacheDiskCacheFactory(context, cacheSize100MegaBytes));*/
        String downPath= Environment.getDownloadCacheDirectory().getPath();
        Log.e("YJL","downPath---->>>"+downPath);
        builder.setDiskCache(new DiskLruCacheFactory(downPath,cacheSize100MegaBytes));
    }

    @Override
    public void registerComponents(Context context, Glide glide) {

    }
}
