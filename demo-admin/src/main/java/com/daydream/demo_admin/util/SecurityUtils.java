package com.daydream.demo_admin.util;

import com.daydream.demo_admin.security.JwtAuthenticatioToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;

import javax.servlet.http.HttpServletRequest;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: 0839d
 * \* Date: 2019/11/18
 * \* Time: 4:58
 * \* To change this template use File | Settings | File Templates.
 * \* Description: Security相关操作
 * \
 */
public class SecurityUtils {
    /**
     * 获取token进行验证
     *
     * @param httpServletRequest
     */
    public static void checkAuthentication(HttpServletRequest httpServletRequest) {
        Authentication authentication = JwtTokenUtils.getAuthenticationeFromToken(httpServletRequest);
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    /**
     * 获取当前登录信息
     *
     * @return
     */
    public static Authentication getAuthentication() {
        if (SecurityContextHolder.getContext() == null) {
            return null;
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication;
    }

    /**
     * 获取当前用户名
     *
     * @return
     */
    public static String getUsername() {
        String username = null;
        Authentication authentication = getAuthentication();
        if (authentication != null) {
            Object principal = authentication.getPrincipal();
            if (principal != null && principal instanceof UserDetails) {
                username = ((UserDetails) principal).getUsername();
            }
        }
        return username;
    }

    /**
     * 获取用户名
     *
     * @return
     */
    public static String getUsername(Authentication authentication) {
        String username = null;
        if (authentication != null) {
            Object principal = authentication.getPrincipal();
            if (principal != null && principal instanceof UserDetails) {
                username = ((UserDetails) principal).getUsername();
            }
        }
        return username;
    }

    /**
     * 登录认证
     *
     * @param httpServletRequest
     * @param username
     * @param password
     * @param authenticationManager
     * @return
     */
    public static JwtAuthenticatioToken login(HttpServletRequest httpServletRequest, String username, String password, AuthenticationManager authenticationManager) {
        //问题
        JwtAuthenticatioToken token = new JwtAuthenticatioToken(username, password);
        token.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
        // 执行登录认证过程
        Authentication authentication = authenticationManager.authenticate(token);
        // 认证成功存储认证信息到上下文
        SecurityContextHolder.getContext().setAuthentication(authentication);
        // 生成令牌并返回给客户端
        token.setToken(JwtTokenUtils.generateToken(authentication));
        return token;
    }

}