package com.example.frescoexercise.source;

import java.util.List;

/**
 * 作者：杨金玲 on 2016/4/22 10:42
 * 邮箱：18363820101@163.com
 */

public interface SrcCreaterImpl {
    //获取一张图片
    public String getPic();

    //获取一组图片
    public List<String> getPicList();

    //获取一张Gif图片
    public String getGif();

    //获取一组Gif图片
    public List<String> getGifList();

}
