// package com.codeman.blog0703.service;
//
// import com.alibaba.fastjson.JSON;
// import com.codeman.blog0703.entity.Role;
// import com.codeman.blog0703.feign.nonfallback.UserFeignService;
// import com.codeman.blog0703.vo.CommonOutVO;
// import lombok.extern.slf4j.Slf4j;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.core.GrantedAuthority;
// import org.springframework.security.core.authority.SimpleGrantedAuthority;
// import org.springframework.security.core.userdetails.User;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.security.core.userdetails.UserDetailsService;
// import org.springframework.security.core.userdetails.UsernameNotFoundException;
// import org.springframework.stereotype.Service;
//
// import java.util.Collections;
// import java.util.Collection;
// import java.util.List;
// import java.util.Objects;
// import java.util.stream.Collectors;
//
// /**
//  * @author: zhanghongjie
//  * @description:
//  * @date: 2021/7/6 9:54
//  * @version: 1.0
//  */
// @Service("userDetailsService")
// @Slf4j
// public class UserDetailsServiceImpl implements UserDetailsService {
//
//     @Autowired
//     private UserFeignService userFeignService;
//
//     @Override
//     public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//         CommonOutVO user = userFeignService.user(username);
//         return new User(username, transPsw(user), transAuths(userFeignService.findAuthsByUsername(username)));
//     }
//
//     private Collection<? extends GrantedAuthority> transAuths(List<Role> authsByUsername) {
//         if (authsByUsername != null && !authsByUsername.isEmpty()) {
//             return authsByUsername.stream()
//                     .filter(Objects::nonNull)
//                     .map(role -> new SimpleGrantedAuthority(role.getRoleName()))
//                     .collect(Collectors.toList());
//         }
//         return Collections.EMPTY_LIST;
//     }
//
//     private String transPsw(CommonOutVO user) {
//         com.codeman.blog0703.entity.User user0 = (com.codeman.blog0703.entity.User) JSON.parse(user.getData());
//         return user0.getPassword();
//     }
// }
