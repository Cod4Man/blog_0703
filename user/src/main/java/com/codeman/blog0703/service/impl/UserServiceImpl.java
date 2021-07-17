package com.codeman.blog0703.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.codeman.blog0703.entity.Role;
import com.codeman.blog0703.entity.User;
import com.codeman.blog0703.entity.UserRoleRelation;
import com.codeman.blog0703.mapper.RoleMapper;
import com.codeman.blog0703.mapper.UserMapper;
import com.codeman.blog0703.mapper.UserRoleRelationMapper;
import com.codeman.blog0703.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

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
    @Resource
    private RoleMapper roleMapper;
    @Resource
    private UserRoleRelationMapper userRoleRelationMapper;

    public User user(String username) {
        User user = userMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getName, username));
        List<UserRoleRelation> userRoleRelations = userRoleRelationMapper.selectList(
                new LambdaQueryWrapper<UserRoleRelation>()
                        .eq(UserRoleRelation::getUserId, user.getUserId())
        );
        user.setRoles(roleMapper.selectBatchIds(
                userRoleRelations.stream()
                .map(UserRoleRelation::getRoleId)
                .collect(Collectors.toList())
        ));
        return user;
    }

    public List<Role> findAuthsByUsername(String username) {
        // TODO 角色逻辑还未写
        return null;
    }
}
