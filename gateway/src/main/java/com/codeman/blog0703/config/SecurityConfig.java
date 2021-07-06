// package com.codeman.blog0703.config;
//
// import com.codeman.blog0703.filter.JwtAuthTokenFilter;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.HttpMethod;
// import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
// import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
// import org.springframework.security.config.http.SessionCreationPolicy;
// import org.springframework.security.core.userdetails.UserDetailsService;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
// /**
//  * @author: zhanghongjie
//  * @description:
//  * @date: 2021/7/6 9:45
//  * @version: 1.0
//  */
// @EnableWebFluxSecurity
// public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
//     @Autowired
//     private UserDetailsService userDetailsService;
//
//     @Autowired
//     private PasswordEncoder bCryptPasswordEncoder;
//
//     @Autowired
//     private JwtAuthTokenFilter jwtAuthTokenFilter;
//
//     @Override
//     protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//         auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
//     }
//
//     @Override
//     protected void configure(HttpSecurity httpSecurity) throws Exception {
//         httpSecurity
//                 // 认证失败处理类
//                 //.exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
//                 // 基于token，所以不需要session,这里设置STATELESS(无状态)是在请求是不生成session
//                 .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
//                 //配置权限
//                 .authorizeRequests()
//                 // login不做拦截
//                 .antMatchers("/login").anonymous()
//                 // 静态文件不拦截
//                 .antMatchers(
//                         HttpMethod.GET,
//                         "/*.html",
//                         "/**/*.html",
//                         "/**/*.css",
//                         "/**/*.js"
//                 ).permitAll()
//                 .antMatchers("/admin/**").hasAnyRole("ADMIN")
//                 .antMatchers("/user/**").hasAnyAuthority("ROLE_USER", "ROLE_USERVIP", "ADMIN")
//                 .antMatchers("/blog/**").hasAnyAuthority("ROLE_USER", "ROLE_USERVIP")
//                 //  除上面外的所有请求全部需要鉴权认证
//                 .anyRequest().authenticated().and()//authenticated()要求在执 行该请求时，必须已经登录了应用
//                 //  CRSF禁用，因为不使用session
//                 .csrf().disable();//禁用跨站csrf攻击防御，否则无法登陆成功
//
//         httpSecurity.addFilterBefore(jwtAuthTokenFilter, UsernamePasswordAuthenticationFilter.class);
//
//     }
// }
