package com.cxkj.blog.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *  Created by Arvin on 2021/2/5.
 *
 *  MD5加密类
 * @parm str 要加密的字符串
 * @return 加密后的字符串
 */

public class MD5Utils {

    public static String code(String str){

        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(str.getBytes());
            byte[] bytesDigest = md.digest();
            int i;
            StringBuffer buffer = new StringBuffer("");
            for (int offset = 0;offset<bytesDigest.length;offset++){
                i = bytesDigest[offset];
                if (i<0)
                    i+= 256;
                if (i<16)
                    buffer.append("0");
                buffer.append(Integer.toHexString(i));
            }
            //32位加密
            return buffer.toString();
            //16位加密
            //return buffer.toString().substring(8,20);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }

    }

    public static void main(String[] args) {
        System.out.println(code("111"));
    }
}
