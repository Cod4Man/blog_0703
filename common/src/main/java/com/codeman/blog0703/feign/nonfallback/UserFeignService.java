package com.codeman.blog0703.feign.nonfallback;

import com.codeman.blog0703.entity.Role;
import com.codeman.blog0703.entity.User;
import com.codeman.blog0703.vo.result.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @author: zhanghongjie
 * @description:
 * @date: 2021/7/6 10:19
 * @version: 1.0
 */
@FeignClient(name = "USER", contextId = "UserFeignServiceNonFallback", path = "/user")
@Configuration
public interface UserFeignService {

    @GetMapping("/{id}")
    Result<User> user(@PathVariable(value = "id") Integer id);

    @GetMapping("/{username}")
    Result<User> user(@PathVariable(value = "username") String username);

    @PostMapping("")
    Result createUser(@RequestBody User user);

    @PutMapping("/{id}")
    Result updateUser(@PathVariable(value = "id") Integer id, @RequestBody User user);

    @GetMapping("/roles/{username}")
    Result<List<Role>> findAuthsByUsername(@PathVariable(value = "username") String username);
}
