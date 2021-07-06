// package com.codeman.blog0703.util;
//
// import com.codeman.blog0703.service.SpringContextHolder;
// import io.jsonwebtoken.Claims;
// import io.jsonwebtoken.Jwts;
// import io.jsonwebtoken.SignatureAlgorithm;
// import org.springframework.beans.factory.annotation.Value;
// import org.springframework.security.core.userdetails.User;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.security.core.userdetails.UserDetailsService;
// import org.springframework.stereotype.Component;
//
// import javax.crypto.spec.SecretKeySpec;
// import javax.xml.bind.DatatypeConverter;
// import java.security.Key;
// import java.util.Date;
// import java.util.HashMap;
// import java.util.Map;
//
// /**
//  * @author: zhanghongjie
//  * @description:
//  * @date: 2021/6/21 23:16
//  * @version: 1.0
//  */
// @Component
// public class JwtUtil {
//
//     private static volatile Key KEY = null;
//
//     private static String secret;
//
//     private static Long expiration ;
//
//     public static String header ;
//
//     public void setSecret(@Value("${blog0703.jwt.secret:asd989234023hkjklfsdjf%^^$&*(!@#$#kjhjkadaksdj}") String secret) {
//         LoggerUtil.info("blog0703.jwt.secret:" + secret, null);
//         JwtUtil.secret = secret;
//     }
//
//     public void setExpiration(@Value("${blog0703.jwt.expiration:3600000}") Long expiration) {
//         JwtUtil.expiration = expiration;
//     }
//
//     public void setHeader(@Value("${blog0703.jwt.header:token}") String header) {
//         JwtUtil.header = header;
//     }
//
//     /**
//      * 验证Token的合法性
//      * @param token
//      * @return
//      */
//     public static boolean verifyToken(String token, UserDetails userDetails) {
//         String username = parseUserName(token);
//         return (username.equals(userDetails.getUsername()) &&
//                 !isTokenExpired(token));
//     }
//
//     private static boolean isTokenExpired(String token) {
//         try {
//             Claims claims = getClaimsFromToken(token);
//             Date expiration = claims.getExpiration();
//             return expiration.before(new Date());
//         } catch (Exception e) {
//             return false;
//         }
//     }
//
//     /**
//      * 解析Token，加工出username
//      * @param token
//      * @return
//      */
//     public static String parseUserName(String token) {
//         String username = null;
//         try {
//             Claims claims = getClaimsFromToken(token);
//             username = claims.get("sub",String.class);
//             LoggerUtil.debug("从令牌中获取用户名:" + username, null);
//         } catch (Exception e) {
//             username = null;
//         }
//         return username;
//     }
//
//     /**
//      * 从令牌中获取数据声明,如果看不懂就看谁调用它
//      *
//      * @param token 令牌
//      * @return 数据声明
//      */
//     private static Claims getClaimsFromToken(String token) {
//         Claims claims = null;
//
//         try {
//             claims = Jwts.parser().setSigningKey(getKeyInstance()).parseClaimsJws(token).getBody();
// //            claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
//         } catch (Exception e) {
//             claims = null;
//         }
//         return claims;
//     }
//
//     public static String geneToken(String username, String password) throws Exception {
//         UserDetailsService userDetailsService = SpringContextHolder.getBean("userDetailsService");
// //        AuthenticationManager authenticationManager = bean.authenticationManagerBean();
// //        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
// //        UserDetail userDetail = (UserDetail) authenticate.getPrincipal();
//         User userDetail = (User) userDetailsService.loadUserByUsername(username);
//         LoggerUtil.debug("[JwtTokenUtils] generateToken "+userDetail.toString(), null);
//         Map<String, Object> claims = new HashMap<>(2);
//         claims.put("sub", userDetail.getUsername());
//         claims.put("created", new Date());
//
//         return generateToken(claims);
//
//     }
//
//     /**
//      * 从claims生成令牌,如果看不懂就看谁调用它
//      *
//      * @param claims 数据声明
//      * @return 令牌
//      */
//     private static String generateToken(Map<String, Object> claims) {
//         Date expirationDate = new Date(System.currentTimeMillis() + expiration);
//         return Jwts.builder().setClaims(claims)
//                 .setExpiration(expirationDate)
//                 .signWith(SignatureAlgorithm.HS256, getKeyInstance())
//                 .compact();
//     }
//
//     private static Key getKeyInstance() {
//         if (KEY == null) {
//             synchronized (JwtUtil.class) {
//                 if (KEY == null) {// 双重锁
//                     byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(secret);
//                     KEY = new SecretKeySpec(apiKeySecretBytes, SignatureAlgorithm.HS256.getJcaName());
//                 }
//             }
//         }
//         return KEY;
//     }
// }
