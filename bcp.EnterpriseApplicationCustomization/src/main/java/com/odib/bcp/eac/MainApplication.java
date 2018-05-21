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
import org.springframework.context.annotation.ComponentScan;

/**
 * <br>
 * 〈主函数〉
 *  @author lizhuo
 * @create 2018/1/3
 * @since 1.0.0
 */
@SpringBootApplication
@ComponentScan("com.odib.bcp.eac")
public class MainApplication {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(MainApplication.class, args);
    }
}
