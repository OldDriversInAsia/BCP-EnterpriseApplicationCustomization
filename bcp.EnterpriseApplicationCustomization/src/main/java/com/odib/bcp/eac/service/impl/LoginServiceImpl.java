/**
 * Copyright (C), 2015-2018, 北京老司机
 * FileName: LoginServiceImpl
 * Author: lizhuo
 * Date: 2018/5/16 上午10:25
 * Description: 登录
 */
package com.odib.bcp.eac.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.odib.bcp.eac.constant.CommonValue;
import com.odib.bcp.eac.service.LoginService;
import com.odib.bcp.eac.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * <br>
 * 〈登录〉
 *  @author lizhuo
 * @create 2018/5/16
 * @since 1.0.0
 */
@Service
public class LoginServiceImpl implements LoginService{
    @Value("${bcp.redis.prefix}")
    private String PREFIX;

    private static final String LOGIN_TOKEN = "login:token:";

    @Autowired
    private RedisService redisService;
    @Override
    public void setLoginToken(String token, Integer idNo) {
        redisService.stringSetValueAndExpireTime(LOGIN_TOKEN + token, String.valueOf(idNo), CommonValue.LOGIN_TOKEN_COOKIE_TIME_HOUR * 60 * 60);
    }

    @Override
    public Integer getLoginToken(String token) {
        String idNoStr = redisService.stringGetStringByKey(LOGIN_TOKEN + token);

        if(StringUtils.isEmpty(idNoStr)){
           return null;
        }
        return Integer.valueOf(idNoStr);
    }

}