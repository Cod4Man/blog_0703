package com.codeman.blog0703.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.codeman.blog0703.entity.Role;
import com.codeman.blog0703.entity.User;
import com.codeman.blog0703.service.IUserService;
import com.codeman.blog0703.util.LoggerUtil;
import com.codeman.blog0703.util.PasswordEncoderUtil;
import com.codeman.blog0703.vo.CommonOutVO;
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
@ApiOperation(value = "用户相关操作")
@RestController
@Slf4j
public class UserController {

    @Resource
    private IUserService userService;


    @ApiOperation(value = "通过ID查询用户")
    @ApiResponses({@ApiResponse(code = 200, message = "OK", response = CommonOutVO.class)})
    @GetMapping("/user/{id}")
    public CommonOutVO user(@PathVariable Integer id) {
        User user = userService.getById(id);
        return new CommonOutVO(200, JSON.toJSONString(user), null);
    }

    @ApiOperation(value = "通过用户名查询用户")
    @ApiResponses({@ApiResponse(code = 200, message = "OK", response = CommonOutVO.class)})
    @GetMapping("/user/{username}")
    public CommonOutVO user(@PathVariable String username) {
        User user = userService.getOne(new LambdaQueryWrapper<User>().eq(User::getName, username));
        return new CommonOutVO(200, JSON.toJSONString(user), null);
    }

    @ApiOperation("通过用户名查找用户所有角色")
    @GetMapping("/roles/{username}")
    public List<Role> findAuthsByUsername(@PathVariable String username) {
        // TODO 角色逻辑还未写
        CommonOutVO user = user(username);
        return null;
    }

    @ApiOperation(value = "创建用户")
    @Transactional
    @PostMapping("/user")
    public CommonOutVO createUser(@RequestBody User user) {
        StringBuffer sb = new StringBuffer();
        verifyUser(user, sb);
        if (sb.length() > 0) {
            return new CommonOutVO(403, null, sb.toString());
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
            return new CommonOutVO(403, null, sb.toString());
        }
        user.setPassword(PasswordEncoderUtil.encode(user.getPassword()));
        boolean save = userService.save(user);
        if (save) {
            LoggerUtil.info("创建用户成功！", null);
        } else {
            LoggerUtil.info("创建用户失败！", null);
        }
        return !save ? new CommonOutVO(403, null, "创建用户失败！") : new CommonOutVO(200, "创建用户成功！", null);
    }

    @ApiOperation(value = "更新用户")
    @Transactional
    @PutMapping("/user/{id}")
    public CommonOutVO updateUser(@PathVariable Integer id, @RequestBody User user) {
        return new CommonOutVO(500, null, "TODO");
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
