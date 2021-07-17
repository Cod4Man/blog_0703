package com.codeman.blog0703.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @author: zhanghongjie
 * @description:
 * @date: 2021/7/5 9:25
 * @version: 1.0
 */
@Component
public class PasswordEncoderUtil {

    private static PasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public void setbCryptPasswordEncoder(PasswordEncoder bCryptPasswordEncoder) {
        PasswordEncoderUtil.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public static String encode(String be4EncodePsw) {
        return bCryptPasswordEncoder.encode(be4EncodePsw);
    }
}
