package com.daydream.demo_admin.security;

import com.daydream.demo_admin.util.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: 0839d
 * \* Date: 2019/11/18
 * \* Time: 4:51
 * \* To change this template use File | Settings | File Templates.
 * \* Description: 登录认证过滤器
 * \
 */
public class JwtAuthenticationFilter extends BasicAuthenticationFilter {
    @Autowired
    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        //拿到token验证登录状态
        SecurityUtils.checkAuthentication(request);
        chain.doFilter(request, response);
    }
}