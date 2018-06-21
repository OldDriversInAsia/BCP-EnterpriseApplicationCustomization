/**
 * Copyright (C), 2015-2018, 北京老司机
 * FileName: WebMvcConfig
 * Author: lizhuo
 * Date: 2018/1/16 15:14
 * Description: Web设置
 */
package com.odib.bcp.eac.config;

import com.odib.bcp.eac.config.interceptor.AdminInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * <br>
 * 〈Web设置〉
 *  @author lizhuo
 * @create 2018/1/16
 * @since 1.0.0
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {
    @Autowired
    AdminInterceptor adminInterceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        super.addInterceptors(registry);
        registry.addInterceptor(adminInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/api/login")
                .excludePathPatterns("/api/user/login");
    }
}
