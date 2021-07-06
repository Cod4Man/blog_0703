package com.codeman.blog0703.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.codeman.blog0703.entity.Role;
import com.codeman.blog0703.entity.User;
import com.codeman.blog0703.mapper.UserMapper;
import com.codeman.blog0703.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

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

    @Resource
    private UserMapper userMapper;

    public User user(String username) {
        User user = userMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getName, username));
        return user;
    }

    public List<Role> findAuthsByUsername(String username) {
        // TODO 角色逻辑还未写
        return null;
    }
}
