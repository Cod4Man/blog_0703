package com.codeman.blog0703.api;

import com.codeman.blog0703.api.fallback.UserFeignFallbackClient;
import com.codeman.blog0703.entity.User;
import com.codeman.blog0703.vo.result.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "USER", fallback = UserFeignFallbackClient.class)
// @Configuration
public interface UserFeignClient {

    @GetMapping("/user/name/{username}")
    Result<User> user(@PathVariable(value = "username")  String username);
}
