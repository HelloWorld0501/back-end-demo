package com.daydream.demo_admin.service;

import com.daydream.demo_admin.model.SysMenu;
import com.daydream.demo_core.service.CrudService;

import java.util.List;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: 0839d
 * \* Date: 2019/10/24
 * \* Time: 22:28
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
public interface SysMenuService extends CrudService<SysMenu> {
    /**
     * 查找菜单树
     *
     * @param userName
     * @param menuType 0:所有菜单与按钮 1:所有菜单不包含按钮!
     * @return
     */
    List<SysMenu> findTree(String userName, int menuType);

    /**
     * 根据用户名查找菜单
     *
     * @param userName
     * @return
     */
    List<SysMenu> findByUser(String userName);
}