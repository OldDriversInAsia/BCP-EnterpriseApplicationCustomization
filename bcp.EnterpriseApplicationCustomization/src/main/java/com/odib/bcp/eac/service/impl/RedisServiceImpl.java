/**
 * Copyright (C), 2015-2018, 北京老司机
 * FileName: RedisServiceImpl
 * Author: lizhuo
 * Date: 2018/1/16 10:33
 * Description: Redis实现类
 */
package com.odib.bcp.eac.service.impl;

import com.odib.bcp.eac.constant.CommonValue;
import com.odib.bcp.eac.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * <br>
 * 〈Redis实现类〉
 *  @author lizhuo
 * @create 2018/1/16
 * @since 1.0.0
 */
@Service
public class RedisServiceImpl implements RedisService{
    @Value("${bcp.redis.prefix}")
    private String PREFIX;

    private static final String LOGIN_TOKEN = "login:token:";

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    private interface Nxxx{
        /**
         * 则只有当key已经存在时才进行set
         */
        String XX = "XX";
        /**
         * 则只有当key不存在时才进行set
         */
        String NX = "NX";
    }
    private interface Expx{
        /**
         * 按秒记
         */
        String EX = "EX";
        /**
         * 按毫秒记
         */
        String PX = "PX";
    }
    @Autowired
    private JedisPool jedisPool;
    @Override
    public void setLoginToken(String token, Integer idNo) {
        Jedis jedis = getJedis();
        jedis.set(LOGIN_TOKEN + token, String.valueOf(idNo), Nxxx.NX, Expx.EX, CommonValue.LOGIN_TOKEN_COOKIE_TIME_HOUR * 60 * 60);
        jedis.close();
    }

    @Override
    public Integer getLoginToken(String token) {
        Jedis jedis = getJedis();
        String idNoStr = jedis.get(LOGIN_TOKEN + token);
        jedis.close();
        if(StringUtils.isEmpty(idNoStr)){
           return null;
        }
        return Integer.valueOf(idNoStr);
    }

    private Jedis getJedis(){
        return jedisPool.getResource();
    }
}
