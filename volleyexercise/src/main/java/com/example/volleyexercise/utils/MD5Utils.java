package com.example.volleyexercise.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 作者：杨金玲 on 2016/4/22 16:53
 * 邮箱：18363820101@163.com
 */

public class MD5Utils {

    public static String md5(String string){
        byte[]secretBytes;
        try {
            secretBytes= MessageDigest.getInstance("md5").digest(string.getBytes());
        } catch (NoSuchAlgorithmException e) {
            throw  new RuntimeException("没有MD5算法");
        }
        String md5Code=new BigInteger(1,secretBytes).toString(16);
        for (int i=0;i<md5Code.length();i++){
            md5Code="0"+md5Code;
        }
        return md5Code;
    }
}
