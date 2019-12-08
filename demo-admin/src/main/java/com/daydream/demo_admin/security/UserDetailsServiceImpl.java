package com.daydream.demo_admin.security;

import com.daydream.demo_admin.model.SysUser;
import com.daydream.demo_admin.service.SysUserService;
import com.daydream.demo_admin.util.ErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: 0839d
 * \* Date: 2019/11/18
 * \* Time: 5:35
 * \* To change this template use File | Settings | File Templates.
 * \* Description: 用户登录认证信息查询
 * \
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private SysUserService sysUserService;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        SysUser sysUser = sysUserService.findByName(userName);
        if (userName == null) {
            throw new UsernameNotFoundException(ErrorCode.USER_NOT_EXITS);
        }
        // 用户权限列表，根据用户拥有的权限标识与如 @PreAuthorize("hasAuthority('sys:menu:view')") 标注的接口对比，决定是否可以调用接口
        Set<String> permissions = sysUserService.findPermission(sysUser.getName());
        List<GrantedAuthority> grantedAuthorities = permissions.stream().map(GrantedAuthorityImpl::new).collect(Collectors.toList());
        return new JwtUserDetails(sysUser.getName(), sysUser.getPassword(), sysUser.getSalt(), grantedAuthorities);
    }
}