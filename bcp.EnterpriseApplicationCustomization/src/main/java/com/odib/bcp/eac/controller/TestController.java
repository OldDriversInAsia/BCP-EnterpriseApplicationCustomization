/**
 * Copyright (C), 2015-2018, 北京老司机
 * FileName: TestController
 * Author: lizhuo
 * Date: 2018/1/3 14:34
 * Description: 测试Controller
 */
package com.odib.bcp.eac.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.JedisPool;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * <br>
 * 〈测试Controller〉
 *  @author lizhuo
 * @create 2018/1/3
 * @since 1.0.0
 */
@RestController
@RequestMapping("/test")
public class TestController {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate redisTemplate;
    @Resource
    private JedisPool jedisPool;

    @RequestMapping("/test")
    public String test(){
        System.out.println("Hello World!");
        return "Hello World!";
    }
}
