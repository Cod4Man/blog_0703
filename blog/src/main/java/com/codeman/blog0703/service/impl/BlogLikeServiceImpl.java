package com.codeman.blog0703.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.codeman.blog0703.entity.BlogLike;
import com.codeman.blog0703.mapper.BlogLikeMapper;
import com.codeman.blog0703.service.IBlogLikeService;
import org.springframework.stereotype.Service;

/**
 * @author: zhanghongjie
 * @description:
 * @date: 2021/7/6 9:25
 * @version: 1.0
 */
@Service
public class BlogLikeServiceImpl extends ServiceImpl<BlogLikeMapper, BlogLike> implements IBlogLikeService {
}
