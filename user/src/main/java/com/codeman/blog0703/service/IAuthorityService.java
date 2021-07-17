package com.codeman.blog0703.service;

import com.codeman.blog0703.entity.Authority;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Zhanghongjie
 * @since 2021-07-03
 */
public interface IAuthorityService extends IService<Authority> {

    boolean refreshPermRolesRules();
}
