/**
 * Copyright (C), 2015-2018, 北京老司机
 * FileName: MainApplication
 * Author: lizhuo
 * Date: 2018/1/3 11:21
 * Description: 主函数
 */
package com.odib.bcp.im;

import com.odib.bcp.im.mars.datacenter.CacheData;
import com.odib.bcp.im.mars.logicserver.ProxySession;
import com.odib.bcp.im.mars.proxy.ProxyServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * <br>
 * 〈主函数〉
 *  @author lizhuo
 * @create 2018/1/3
 * @since 1.0.0
 */
@SpringBootApplication
public class MainApplication {
    public static void main(String[] args){
        new Thread(() -> {
            try {
                // init non-persistent database
                CacheData.connect();
                ProxySession.Manager.connect();

                int port;
                if (args.length > 0) {
                    port = Integer.parseInt(args[0]);
                } else {
                    port = 8081;
                }
                new ProxyServer(port).start();

                ProxySession.Manager.disconnect();
                CacheData.disconnect();
            }catch (Exception e){

            }
        }).start();
        SpringApplication.run(MainApplication.class, args);
    }
}
