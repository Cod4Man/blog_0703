package com.codeman.blog0703.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.lang.Assert;
import com.codeman.blog0703.config.JwtGenerator;
import com.codeman.blog0703.constants.AuthConstants;
import com.codeman.blog0703.entity.User;
import com.codeman.blog0703.service.IAuthService;
import com.codeman.blog0703.vo.OAuthToken;
import com.codeman.blog0703.vo.result.Result;
import com.codeman.blog0703.vo.result.ResultCode;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author haoxr
 * @description 微信小程序认证接口
 * @createTime 2021/5/20 23:37
 */
@Service("authService")
public class AuthServiceImpl implements IAuthService {

    @Autowired
    private JwtGenerator jwtGenerator;


    @SneakyThrows
    @Override
    public OAuthToken login(String code, User userInfo) {
        // WxMaJscode2SessionResult sessionInfo = wxMaService.getUserService().getSessionInfo(code);
        // String openid = sessionInfo.getOpenid();
        // Result<UmsMember> result = memberFeignClient.getByOpenid(openid);
        // UmsMember member;
        // if (ResultCode.USER_NOT_EXIST.getCode().equals(result.getCode())) {
        //     // 用户不存在，注册成为新用户
        //     member = new UmsMember();
        //     BeanUtil.copyProperties(userInfo, member);
        //     member.setOpenid(openid);
        //     Result<Long> addRes = memberFeignClient.add(member);
        //     Assert.isTrue(ResultCode.SUCCESS.getCode().equals(addRes.getCode()), "微信用户注册失败");
        //     member.setId(addRes.getData()); // 新增后有了会员ID
        // } else {
        //     member = result.getData();
        // }

        // 自定义JWT生成
        // 1. JWT授权，一般存放用户的角色标识，用于资源服务器（网关）鉴权
        Set<String> authorities = new HashSet<>();
        // 2. JWT增强，携带用户ID等信息
        Map<String, String> additional = new HashMap<>();
        additional.put(AuthConstants.USER_NAME_KEY, Convert.toStr(userInfo.getName()));
        String accessToken = jwtGenerator.createAccessToken(authorities, additional);

        OAuthToken token = new OAuthToken().accessToken(accessToken);
        return token;
    }
}
