package com.codeman.blog0703;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author: zhanghongjie
 * @description:
 * @date: 2021/7/6 9:02
 * @version: 1.0
 */
@SpringBootApplication
@MapperScan(value = "com.codeman.blog0703.mapper")
public class BlogApp {

    public static void main(String[] args) {
        SpringApplication.run(BlogApp.class, args);
    }
}
