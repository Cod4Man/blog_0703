package com.codeman.blog0703.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.codeman.blog0703.constants.GlobalConstants;
import com.codeman.blog0703.entity.Authority;
import com.codeman.blog0703.entity.Role;
import com.codeman.blog0703.entity.RoleAuthRelation;
import com.codeman.blog0703.mapper.AuthorityMapper;
import com.codeman.blog0703.mapper.RoleAuthRelationMapper;
import com.codeman.blog0703.mapper.RoleMapper;
import com.codeman.blog0703.service.IAuthorityService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
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
public class AuthorityServiceImpl extends ServiceImpl<AuthorityMapper, Authority> implements IAuthorityService {

    @Autowired
    private RedisTemplate redisTemplate;

    @Resource
    private RoleMapper roleMapper;
    @Resource
    private RoleAuthRelationMapper roleAuthRelationMapper;

    public List<Authority> completeAuthority(Wrapper<Authority> wrapper) {
        List<Authority> authorities = this.baseMapper.selectList(wrapper);
        return authorities.stream()
                .filter(Objects::nonNull)
                .peek(authority -> {
                    try {
                        Integer authId = authority.getAuthId();
                        // 查询出roleIds
                        List<RoleAuthRelation> roleAuthRelations = roleAuthRelationMapper.selectList(
                                new LambdaQueryWrapper<RoleAuthRelation>()
                                        .eq(RoleAuthRelation::getAuthId, authId));
                        // 查询复制roleCDList
                        authority.setRoleList(roleMapper.selectList(
                                new LambdaQueryWrapper<Role>()
                                .in(Role::getRoleId, roleAuthRelations.stream()
                                        .map(RoleAuthRelation::getRoleId)
                                        .collect(Collectors.toList()))

                        ));
                    } catch (Exception e) {
                        log.error("查询角色权限时异常===================", e);
                    }
                })
                .collect(Collectors.toList());
    }

    @Override
    public boolean refreshPermRolesRules() {
        {
            redisTemplate.delete(Arrays.asList(GlobalConstants.URL_PERM_ROLES_KEY,GlobalConstants.BTN_PERM_ROLES_KEY));
            List<Authority> permissions = this.completeAuthority(null);
            if (CollectionUtil.isNotEmpty(permissions)) {
                // 初始化URL【权限->角色(集合)】规则
                List<Authority> urlPermList = permissions.stream()
                        .filter(item -> StrUtil.isNotBlank(item.getAuthMenuPath()))
                        .collect(Collectors.toList());
                if (CollectionUtil.isNotEmpty(urlPermList)) {
                    Map<String, List<String>> urlPermRoles = new HashMap<>();
                    urlPermList.stream().forEach(item -> {
                        String perm = item.getAuthMenuPath();
                        List<String> roles = item.getRoleList().stream()
                                .map(Role::getRoleCd)
                                .collect(Collectors.toList());
                        urlPermRoles.put(perm, roles);
                    });
                    redisTemplate.opsForHash().putAll(GlobalConstants.URL_PERM_ROLES_KEY, urlPermRoles);
                }
                // 初始化URL【按钮->角色(集合)】规则
                /*List<SysPermission> btnPermList = permissions.stream()
                        .filter(item -> StrUtil.isNotBlank(item.getBtnPerm()))
                        .collect(Collectors.toList());
                if (CollectionUtil.isNotEmpty(btnPermList)) {
                    Map<String, List<String>> btnPermRoles = CollectionUtil.newHashMap();
                    btnPermList.stream().forEach(item -> {
                        String perm = item.getBtnPerm();
                        List<String> roles = item.getRoles();
                        btnPermRoles.put(perm, roles);
                    });
                    redisTemplate.opsForHash().putAll(GlobalConstants.BTN_PERM_ROLES_KEY, btnPermRoles);
                }*/
            }
            return true;
        }
    }
}
