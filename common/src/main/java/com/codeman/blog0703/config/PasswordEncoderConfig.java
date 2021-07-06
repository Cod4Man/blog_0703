package com.codeman.blog0703.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author: zhanghongjie
 * @description:
 * @date: 2021/7/5 9:39
 * @version: 1.0
 */
@Configuration
public class PasswordEncoderConfig {

    @Bean
    public PasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder(6);
    }
}
