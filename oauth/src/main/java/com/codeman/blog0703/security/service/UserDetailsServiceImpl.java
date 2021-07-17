package com.codeman.blog0703.security.service;

import com.codeman.blog0703.api.UserFeignClient;
import com.codeman.blog0703.domain.OAuthUserDetails;
import com.codeman.blog0703.entity.User;
import com.codeman.blog0703.enums.OAuthClientEnum;
import com.codeman.blog0703.util.JwtUtils;
import com.codeman.blog0703.vo.result.Result;
import com.codeman.blog0703.vo.result.ResultCode;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * 【重要】从数据库获取用户信息，用于和前端传过来的用户信息进行密码判读
 * @author haoxr
 * @date 2020-05-27
 */
@Service
@AllArgsConstructor
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserFeignClient userFeignClient;
    // private MemberFeignClient memberFeignClient;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String clientId = JwtUtils.getAuthClientId();
        OAuthClientEnum client = OAuthClientEnum.getByClientId(clientId);

        Result result;
        OAuthUserDetails oauthUserDetails = null;
        switch (client) {
            default:
                result = userFeignClient.user(username);
                if (ResultCode.SUCCESS.getCode().equals(result.getCode())) {
                    User sysUser = (User)result.getData();
                    oauthUserDetails = new OAuthUserDetails(sysUser);
                }
                break;
        }
        if (oauthUserDetails == null || oauthUserDetails.getId() == null) {
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

}
