package com.codeman.blog0703.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: zhanghongjie
 * @description:
 * @date: 2021/7/6 9:26
 * @version: 1.0
 */
@RestController
@Api("博客Controller")
@RequestMapping("/blog")
public class BlogController {

    @ApiOperation("测试hello方法")
    @GetMapping("/hello")
    public String hello() {
        return "hello blog";
    }
}
