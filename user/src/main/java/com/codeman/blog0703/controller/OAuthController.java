package com.codeman.blog0703.controller;

import com.codeman.blog0703.entity.User;
import com.codeman.blog0703.service.IAuthService;
import com.codeman.blog0703.vo.OAuthToken;
import com.codeman.blog0703.vo.result.Result;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.security.KeyPair;
import java.security.Principal;
import java.security.interfaces.RSAPublicKey;
import java.util.Map;

@Api(tags = "认证中心")
@RestController
@RequestMapping("/oauth")
@Slf4j
public class OAuthController {

    @Autowired
    private TokenEndpoint tokenEndpoint;
    @Autowired
    private IAuthService authService;
    @Autowired
    private KeyPair keyPair;

    @ApiOperation(value = "OAuth2认证", notes = "login")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "grant_type", defaultValue = "password", value = "授权模式", required = true),
            @ApiImplicitParam(name = "client_id", value = "Oauth2客户端ID（新版本需放置请求头）", required = true),
            @ApiImplicitParam(name = "client_secret", value = "Oauth2客户端秘钥（新版本需放置请求头）", required = true),
            @ApiImplicitParam(name = "refresh_token", value = "刷新token"),
            @ApiImplicitParam(name = "username", defaultValue = "admin", value = "登录用户名"),
            @ApiImplicitParam(name = "password", defaultValue = "123456", value = "登录密码")
    })
    @PostMapping("/token")
    public Object postAccessToken(
            @ApiIgnore Principal principal,
            @ApiIgnore @RequestParam Map<String, String> parameters
    ) throws HttpRequestMethodNotSupportedException {

        /**
         * 获取登录认证的客户端ID
         *
         * 兼容两种方式获取Oauth2客户端信息（client_id、client_secret）
         * 方式一：client_id、client_secret放在请求路径中(注：当前版本已废弃)
         * 方式二：放在请求头（Request Headers）中的Authorization字段，且经过加密，例如 Basic Y2xpZW50OnNlY3JldA== 明文等于 client:secret
         */
        /*String clientId = JwtUtils.getAuthClientId();
        OAuthClientEnum client = OAuthClientEnum.getByClientId(clientId);
        switch (client) {
            case TEST: // knife4j接口测试文档使用 client_id/clien client/123456
                return tokenEndpoint.postAccessToken(principal, parameters).getBody();
            default:
                return Result.success(tokenEndpoint.postAccessToken(principal, parameters).getBody());
        }*/
        return Result.success(tokenEndpoint.postAccessToken(principal, parameters).getBody());
    }

    @ApiOperation(value = "登录")
    @ApiImplicitParam(name = "code", value = "小程序授权code", paramType = "path")
    @PostMapping("/token/{code}")
    public Result<OAuthToken> wechatLogin(@PathVariable String code, @RequestBody User userInfo) {
        OAuthToken token = authService.login(code, userInfo);
        return Result.success(token);
    }

    /*
    @ApiOperation(value = "注销", notes = "logout")
    @DeleteMapping("/logout")
    public Result logout() {
        JSONObject payload = JwtUtils.getJwtPayload();
        String jti = payload.getStr(AuthConstants.JWT_JTI); // JWT唯一标识
        Long expireTime = payload.getLong(AuthConstants.JWT_EXP); // JWT过期时间戳(单位：秒)
        if (expireTime != null) {
            long currentTime = System.currentTimeMillis() / 1000;// 当前时间（单位：秒）
            if (expireTime > currentTime) { // token未过期，添加至缓存作为黑名单限制访问，缓存时间为token过期剩余时间
                redisTemplate.opsForValue().set(AuthConstants.TOKEN_BLACKLIST_PREFIX + jti, null, (expireTime - currentTime), TimeUnit.SECONDS);
            }
        } else { // token 永不过期则永久加入黑名单
            redisTemplate.opsForValue().set(AuthConstants.TOKEN_BLACKLIST_PREFIX + jti, null);
        }
        return Result.success("注销成功");
    }*/

    @ApiOperation(value = "获取公钥", notes = "login")
    @GetMapping("/public-key")
    public Map<String, Object> getPublicKey() {
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        RSAKey key = new RSAKey.Builder(publicKey).build();
        return new JWKSet(key).toJSONObject();
    }
}
