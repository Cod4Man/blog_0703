package com.codeman.blog0703.routh;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.codeman.blog0703.constants.AuthConstants;
import com.codeman.blog0703.util.LoggerUtil;
import com.nimbusds.jose.JWSObject;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.util.Strings;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;


/**
 * @author: zhanghongjie
 * @description:
 * @date: 2021/5/29 10:04
 * @version: 1.0
 */
@Component
public class CommonRouth implements GlobalFilter, Ordered {


    @SneakyThrows
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();

        // 演示环境禁止删除和修改
        /*if (isDemoEnv
                && (HttpMethod.DELETE.toString().equals(request.getMethodValue()) // 删除方法
                || HttpMethod.PUT.toString().equals(request.getMethodValue())) // 修改方法
        ) {
            return ResponseUtils.writeErrorInfo(response, ResultCode.FORBIDDEN_OPERATION);
        }*/

        // 非JWT或者JWT为空不作处理
        String token = request.getHeaders().getFirst(AuthConstants.AUTHORIZATION_KEY);
        if (StringUtils.isBlank(token) || !token.startsWith(AuthConstants.AUTHORIZATION_PREFIX)) {
            return chain.filter(exchange);
        }

        // 解析JWT获取jti，以jti为key判断redis的黑名单列表是否存在，存在拦截响应token失效
        token = token.replace(AuthConstants.AUTHORIZATION_PREFIX, Strings.EMPTY);
        JWSObject jwsObject = JWSObject.parse(token);
        String payload = jwsObject.getPayload().toString();
        JSONObject jsonObject = JSONUtil.parseObj(payload);
        String jti = jsonObject.getStr(AuthConstants.JWT_JTI);
        // TODO Redis后续操作
        /*Boolean isBlack = redisTemplate.hasKey(AuthConstants.TOKEN_BLACKLIST_PREFIX + jti);
        if (isBlack) {
            return ResponseUtils.writeErrorInfo(response, ResultCode.TOKEN_ACCESS_FORBIDDEN);
        }*/

        // 存在token且不是黑名单，request写入JWT的载体信息
        request = exchange.getRequest().mutate()
                .header(AuthConstants.JWT_PAYLOAD_KEY, payload)
                .build();
        exchange = exchange.mutate().request(request).build();
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
