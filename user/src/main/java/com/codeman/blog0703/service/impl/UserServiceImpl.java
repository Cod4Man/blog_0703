package com.codeman.blog0703.service.impl;

import com.codeman.blog0703.entity.User;
import com.codeman.blog0703.mapper.UserMapper;
import com.codeman.blog0703.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Zhanghongjie
 * @since 2021-07-03
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
