package com.codeman.blog0703;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author: zhanghongjie
 * @description:
 * @date: 2021/7/3 16:33
 * @version: 1.0
 */
@SpringBootApplication
@MapperScan(basePackages={"com.codeman.blog0703.mapper"})
public class UserAPP {
    public static void main(String[] args) {
        SpringApplication.run(UserAPP.class, args);
    }
}
