/**
 * Copyright (C), 2015-2018, 北京老司机
 * FileName: PasswordUtil
 * Author: lizhuo
 * Date: 2018/1/12 14:02
 * Description: 密码处理类
 */
package com.odib.bcp.eac.util;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * <br>
 * 〈密码处理类〉
 *  @author lizhuo
 * @create 2018/1/12
 * @since 1.0.0
 */
public class PasswordUtil {
    private static final int SALT_LENGTH = 10;

    public static String passwordHash(String password, String salt){
        String result = DigestUtils.sha1Hex(password + salt);
        return result;
    }

    public static String createSalt(){
        String md5 = DigestUtils.md5Hex(Long.toString(System.currentTimeMillis()).getBytes());
        return md5.substring(0, SALT_LENGTH);
    }
}
