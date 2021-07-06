// package com.codeman.blog0703.filter;
//
// import com.codeman.blog0703.util.JwtUtil;
// import lombok.extern.slf4j.Slf4j;
// import org.apache.commons.lang3.StringUtils;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
// import org.springframework.security.core.context.SecurityContextHolder;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.security.core.userdetails.UserDetailsService;
// import org.springframework.stereotype.Component;
// import org.springframework.web.filter.OncePerRequestFilter;
//
// import javax.servlet.ServletException;
// import javax.servlet.FilterChain;
// import javax.servlet.http.HttpServletRequest;
// import javax.servlet.http.HttpServletResponse;
// import java.io.IOException;
//
// /**
//  * @author: zhanghongjie
//  * @description:
//  * @date: 2021/7/6 10:04
//  * @version: 1.0
//  */
// @Component
// @Slf4j
// public class JwtAuthTokenFilter extends OncePerRequestFilter {
//
//     @Autowired
//     private UserDetailsService userDetailsService;
//
//     @Override
//     protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
//             throws ServletException, IOException {
//         String token = request.getHeader(JwtUtil.header);
//         if (StringUtils.isNotBlank(token)) {
//             String userName = JwtUtil.parseUserName(token);
//             if (StringUtils.isNotBlank(userName) && SecurityContextHolder.getContext().getAuthentication()==null) {
//                 UserDetails userDetails = userDetailsService.loadUserByUsername(userName);
//                 if (JwtUtil.verifyToken(token, userDetails)) {
//                     //给使用该JWT令牌的用户进行授权
//                     UsernamePasswordAuthenticationToken authenticationToken =
//                             new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
//                     //设置用户身份授权
//                     SecurityContextHolder.getContext().setAuthentication(authenticationToken);
//                 }
//             }
//         }
//         filterChain.doFilter(request, response);
//     }
// }
