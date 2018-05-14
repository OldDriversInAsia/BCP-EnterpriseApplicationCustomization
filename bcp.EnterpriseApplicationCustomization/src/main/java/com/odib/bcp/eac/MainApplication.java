/**
 * Copyright (C), 2015-2018, 北京老司机
 * FileName: MainApplication
 * Author: lizhuo
 * Date: 2018/1/3 11:21
 * Description: 主函数
 */
package com.odib.bcp.eac;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.security.MessageDigest;

/**
 * <br>
 * 〈主函数〉
 *  @author lizhuo
 * @create 2018/1/3
 * @since 1.0.0
 */
@SpringBootApplication
public class MainApplication {
    public static void main(String[] args) throws Exception{
        //SpringApplication.run(MainApplication.class, args);
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        byte[] digest = md5.digest("lang=zh&location=116.40,39.9&t=1526286792&unit=m&username=HE18050813365716470850d8f6b3f942008cd753614a8d4f34".getBytes("UTF-8"));
        Integer.parseInt("", 16);
        String value = "4f32b09055fc0aa8b458cff4c58ace95";
        byte[] bytes = toBytes(value);
        System.out.println(bytes);
    }
    public static byte[] toBytes(String str) {
        if(str == null || str.trim().equals("")) {
            return new byte[0];
        }

        byte[] bytes = new byte[str.length() / 2];
        for(int i = 0; i < str.length() / 2; i++) {
            String subStr = str.substring(i * 2, i * 2 + 2);
            bytes[i] = (byte) Integer.parseInt(subStr, 16);
        }

        return bytes;
    }
}
