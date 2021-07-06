package com.codeman.blog0703;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author: zhanghongjie
 * @description:
 * @date: 2021/7/5 22:42
 * @version: 1.0
 */
@SpringBootApplication
@EnableFeignClients
public class GatewayApp {

    public static void main(String[] args) {
        SpringApplication.run(GatewayApp.class, args);
    }
}
