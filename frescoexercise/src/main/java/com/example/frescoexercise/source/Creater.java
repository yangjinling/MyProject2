package com.example.frescoexercise.source;

import java.util.List;

/**
 * 作者：杨金玲 on 2016/4/22 10:46
 * 邮箱：18363820101@163.com
 */

public class Creater {
    /**
     * 13     * 图片来源
     * 14
     */
    public interface Type {
        public static final int NET = 1;
        public static final int LOCAL = 2;
    }


    private SrcCreaterImpl mSrcCreater;


    /**
     * 23     * 初始化类型
     * 24
     */
    public void init(int type) {
        switch (type) {
            case Type.LOCAL:
                mSrcCreater = new LocalSrcCreater();
                break;
            case Type.NET:
                mSrcCreater = new NetSrcCreater();
                break;
            default:
                throw new IllegalArgumentException("非法类型参数!");
        }
    }

    public String getPic() {
        if (mSrcCreater == null) {
            throw new IllegalArgumentException("请先初始化类型参数!");
        }


        return mSrcCreater.getPic();
    }

    public List<String> getPicList() {
        if (mSrcCreater == null) {
            throw new IllegalArgumentException("请先初始化类型参数!");
        }
        return mSrcCreater.getPicList();
    }

    public String getGif() {
        if (mSrcCreater == null) {
            throw new IllegalArgumentException("请先初始化类型参数!");
        }
        return mSrcCreater.getGif();
    }

    public List<String> getGifList() {
        if (mSrcCreater == null) {
            throw new IllegalArgumentException("请先初始化类型参数!");
        }


        return mSrcCreater.getGifList();
    }

}
