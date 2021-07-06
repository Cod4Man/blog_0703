package com.codeman.blog0703.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.codeman.blog0703.entity.Role;
import com.codeman.blog0703.entity.User;
import com.codeman.blog0703.service.IUserService;
import com.codeman.blog0703.util.LoggerUtil;
import com.codeman.blog0703.util.PasswordEncoderUtil;
import com.codeman.blog0703.vo.result.Result;
import com.codeman.blog0703.vo.result.ResultCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author: zhanghongjie
 * @description:
 * @date: 2021/7/3 17:00
 * @version: 1.0
 */
@Api(value = "用户相关操作")
@RestController
@Slf4j
public class UserController {

    @Resource
    private IUserService userService;


    @ApiOperation(value = "通过ID查询用户")
    @ApiResponses({@ApiResponse(code = 200, message = "OK", response = Result.class)})
    @GetMapping("/user/{id}")
    public Result<User> user(@PathVariable Integer id) {
        User user = userService.getById(id);
        return Result.success(user);
    }

    @ApiOperation(value = "通过用户名查询用户")
    @ApiResponses({@ApiResponse(code = 200, message = "OK", response = Result.class)})
    @GetMapping("/user/{username}")
    public Result<User> user(@PathVariable String username) {
        return Result.success(userService.user(username));
    }

    @ApiOperation("通过用户名查找用户所有角色")
    @GetMapping("/roles/{username}")
    public Result<List<Role>> findAuthsByUsername(@PathVariable String username) {
        // TODO 角色逻辑还未写
        List<Role> roles = userService.findAuthsByUsername(username);
        return Result.success();
    }

    @ApiOperation(value = "创建用户")
    @Transactional
    @PostMapping("/user")
    public Result createUser(@RequestBody User user) {
        StringBuffer sb = new StringBuffer();
        verifyUser(user, sb);
        if (sb.length() > 0) {
            return Result.failed(sb.toString());
        }
        List<User> list = userService.list(new LambdaQueryWrapper<User>()
                .eq(User::getPhoneNum, user.getPhoneNum())
        );
        if (!CollectionUtils.isEmpty(list)) {
            sb.append("该【电话号码】已被注册！");
        }
        List<User> list2 = userService.list(new LambdaQueryWrapper<User>().eq(User::getName, user.getName()));
        if (!CollectionUtils.isEmpty(list2)) {
            sb.append("该【用户名】已被注册！");
        }
        if (sb.length() > 0) {
            return Result.failed(sb.toString());
        }
        user.setPassword(PasswordEncoderUtil.encode(user.getPassword()));
        boolean save = userService.save(user);
        if (save) {
            LoggerUtil.info("创建用户成功！", null);
        } else {
            LoggerUtil.info("创建用户失败！", null);
        }
        return !save ? Result.failed("创建用户失败！") : Result.success( "创建用户成功！");
    }

    @ApiOperation(value = "更新用户")
    @Transactional
    @PutMapping("/user/{id}")
    public Result updateUser(@PathVariable Integer id, @RequestBody User user) {
        return Result.success();
    }

    /**
     * 校验User
     *
     * @param user
     * @param sb
     */
    private void verifyUser(User user, StringBuffer sb) {
        if (user == null) {
            sb.append("用户信息输入异常，请重试！");
        } else {
            if (StringUtils.isBlank(user.getPhoneNum())) {
                sb.append("用户信息【电话号码】不可为空!");
            }
            if (StringUtils.isBlank(user.getName())) {
                sb.append("用户信息【用户名】不可为空!");
            }
            if (StringUtils.isBlank(user.getPassword())) {
                sb.append("用户信息【密码】不可为空!");
            }
            if (StringUtils.isBlank(user.getNickname())) {
                sb.append("用户信息【昵称】不可为空!");
            }

        }
    }

}
