/**
 * Copyright (C), 2015-2018, 北京老司机
 * FileName: LoginService
 * Author: lizhuo
 * Date: 2018/5/16 上午10:25
 * Description: 登录
 */
package com.odib.bcp.eac.service;

/**
 * <br>
 * 〈登录〉
 *  @author lizhuo
 * @create 2018/5/16
 * @since 1.0.0
 */
public interface LoginService {
    void setLoginToken(String token, Integer idNo);
    Integer getLoginToken(String token);
}