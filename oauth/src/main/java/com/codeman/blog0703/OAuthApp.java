package com.codeman.blog0703;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author: zhanghongjie
 * @description:
 * @date: 2021/7/16 22:30
 * @version: 1.0
 */
@SpringBootApplication
@EnableFeignClients
public class OAuthApp {

    public static void main(String[] args) {
        SpringApplication.run(OAuthApp.class, args);
    }
}
