package com.codeman.blog0703.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.codeman.blog0703.entity.Blog;
import com.codeman.blog0703.mapper.BlogMapper;
import com.codeman.blog0703.service.IBlogService;
import org.springframework.stereotype.Service;

/**
 * @author: zhanghongjie
 * @description:
 * @date: 2021/7/6 9:13
 * @version: 1.0
 */
@Service
public class BlogServiceImpl extends ServiceImpl<BlogMapper, Blog> implements IBlogService {
}
