package com.codeman.blog0703.component;

import com.codeman.blog0703.service.IAuthorityService;
import com.codeman.blog0703.service.IRoleService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

/**
 * 容器启动完成时加载角色权限规则至Redis缓存
 */
@Component
// @DependsOn(value = "authorityService")
public class InitPermissionRoles implements CommandLineRunner {

    @Autowired
    private IAuthorityService authorityService;

    @Override
    public void run(String... args) {
        authorityService.refreshPermRolesRules();
    }
}
