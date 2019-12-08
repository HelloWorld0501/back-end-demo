package com.daydream.demo_admin.service;

import com.daydream.demo_admin.model.SysUser;
import com.daydream.demo_admin.model.SysUserRole;
import com.daydream.demo_core.page.PageRequest;
import com.daydream.demo_core.service.CrudService;

import java.io.File;
import java.util.List;
import java.util.Set;

public interface SysUserService extends CrudService<SysUser> {

    /**
     * 查找所有用户
     *
     * @return
     */
    List<SysUser> findAll();

    SysUser findByName(String username);

    /**
     * 查找用户拥有的权限
     *
     * @param userName
     * @return
     */
    Set<String> findPermission(String userName);

    /**
     * 查找用户所拥有的角色
     *
     * @param userId
     * @return
     */
    List<SysUserRole> findUserRoles(Long userId);

    /**
     * 生成用户Excel文件
     *
     * @param pageRequest 要导出的分页查询参数
     * @return
     */
    File createUserExcelFile(PageRequest pageRequest);
}
