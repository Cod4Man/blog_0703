package com.codeman.blog0703.feign.nonfallback;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.codeman.blog0703.entity.Role;
import com.codeman.blog0703.entity.User;
import com.codeman.blog0703.util.LoggerUtil;
import com.codeman.blog0703.util.PasswordEncoderUtil;
import com.codeman.blog0703.vo.CommonOutVO;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Collection;
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
    CommonOutVO user(@PathVariable(value = "id") Integer id);

    @GetMapping("/{username}")
    CommonOutVO user(@PathVariable(value = "username") String username);

    @PostMapping("")
    CommonOutVO createUser(@RequestBody User user);

    @PutMapping("/{id}")
    CommonOutVO updateUser(@PathVariable(value = "id") Integer id, @RequestBody User user);

    @GetMapping("/roles/{username}")
    List<Role> findAuthsByUsername(@PathVariable(value = "username") String username);
}
