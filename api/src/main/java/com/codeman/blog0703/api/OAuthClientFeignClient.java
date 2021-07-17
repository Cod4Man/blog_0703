package com.codeman.blog0703.api;

import com.codeman.blog0703.entity.SysOauthClient;
import com.codeman.blog0703.vo.result.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "USER", contextId = "oauth-client")
// @Configuration
public interface OAuthClientFeignClient {

    @GetMapping("/users/oauth-clients/{clientId}")
    Result<SysOauthClient> getOAuthClientById(@PathVariable(name = "clientId") String clientId);
}
