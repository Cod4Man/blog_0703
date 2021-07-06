package com.codeman.blog0703.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.codeman.blog0703.entity.Role;
import com.codeman.blog0703.entity.User;
import com.codeman.blog0703.service.IUserService;
import com.codeman.blog0703.vo.result.Result;
import com.codeman.blog0703.vo.result.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 【重要】从数据库获取用户信息，用于和前端传过来的用户信息进行密码判读
 *
 * @author haoxr
 * @date 2020-05-27
 */
@Service("userDetailsService")
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private IUserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // String clientId = JwtUtils.getAuthClientId();
        // OAuthClientEnum client = OAuthClientEnum.getByClientId(clientId);
        // String clientId = JwtUtils.getAuthClientId();
        // OAuthClientEnum client = OAuthClientEnum.getByClientId(clientId);

        org.springframework.security.core.userdetails.User oauthUserDetails = null;
        switch (1) {
            default:
                User user = userService.user(username);
                oauthUserDetails = new org.springframework.security.core.userdetails.User(user.getName(), user.getPassword(), transAuths(userService.findAuthsByUsername(username)));
                break;
        }
        if (oauthUserDetails == null || oauthUserDetails.getUsername() == null) {
            throw new UsernameNotFoundException(ResultCode.USER_NOT_EXIST.getMsg());
        } else if (!oauthUserDetails.isEnabled()) {
            throw new DisabledException("该账户已被禁用!");
        } else if (!oauthUserDetails.isAccountNonLocked()) {
            throw new LockedException("该账号已被锁定!");
        } else if (!oauthUserDetails.isAccountNonExpired()) {
            throw new AccountExpiredException("该账号已过期!");
        }
        return oauthUserDetails;
    }

    private Collection<? extends GrantedAuthority> transAuths(List<Role> roles) {
        if (roles != null && !roles.isEmpty()) {
            return roles.stream()
                    .filter(Objects::nonNull)
                    .map(role -> new SimpleGrantedAuthority(role.getRoleName()))
                    .collect(Collectors.toList());
        }
        return Collections.EMPTY_LIST;
    }

}
