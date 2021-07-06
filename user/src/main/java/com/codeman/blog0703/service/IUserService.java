package com.codeman.blog0703.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.codeman.blog0703.entity.Role;
import com.codeman.blog0703.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.codeman.blog0703.vo.result.Result;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Zhanghongjie
 * @since 2021-07-03
 */
public interface IUserService extends IService<User> {

    User user( String username);

    List<Role> findAuthsByUsername(String username);
}
