/**
 * Copyright (C), 2015-2018, 北京老司机
 * FileName: CommonValue
 * Author: lizhuo
 * Date: 2018/1/16 15:00
 * Description: 公共常量
 */
package com.odib.bcp.eac.constant;

/**
 * <br>
 * 〈公共常量〉
 *  @author lizhuo
 * @create 2018/1/16
 * @since 1.0.0
 */
public class CommonValue {
    /**
     * 登录token存在cookie的键
    */
    public static final String LOGIN_TOKEN_COOKIE_KEY = "BCP_SSO";
    /**
     * cookie的path值
     */
    public static final String LOGIN_TOKEN_COOKIE_PATH = "/";
    /**
     * token在cookie中的存活时间(小时)
     */
    public static final Integer LOGIN_TOKEN_COOKIE_TIME_HOUR = 48;
}
