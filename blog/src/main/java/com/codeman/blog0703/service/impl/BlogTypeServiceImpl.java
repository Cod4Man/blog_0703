package com.codeman.blog0703.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.codeman.blog0703.entity.BlogType;
import com.codeman.blog0703.mapper.BlogTypeMapper;
import com.codeman.blog0703.service.IBlogTypeService;
import org.springframework.stereotype.Service;

/**
 * @author: zhanghongjie
 * @description:
 * @date: 2021/7/6 9:09
 * @version: 1.0
 */
@Service
public class BlogTypeServiceImpl extends ServiceImpl<BlogTypeMapper,BlogType> implements IBlogTypeService {
}
