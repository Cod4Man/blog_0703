package com.codeman.blog0703.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author: zhanghongjie
 * @description:
 * @date: 2021/7/3 14:37
 * @version: 1.0
 */
@Slf4j
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Test {

    @Override
    public String toString() {
        return "Test{" +
                "a=" + a +
                '}';
    }

    private int a;

    public static void main(String[] args) {
        System.out.println(new Test(1));
    }
}
