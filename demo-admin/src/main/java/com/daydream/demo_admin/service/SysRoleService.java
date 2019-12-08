package com.daydream.demo_admin.service;

import com.daydream.demo_admin.model.SysMenu;
import com.daydream.demo_admin.model.SysRole;
import com.daydream.demo_admin.model.SysRoleMenu;
import com.daydream.demo_core.service.CrudService;

import java.util.List;

public interface SysRoleService extends CrudService<SysRole> {
    /**
     * 查找所有
     *
     * @return
     */
    List<SysRole> findAll();

    /**
     * 查找角色菜单集合
     *
     * @param roleId
     * @return
     */
    List<SysMenu> findRoleMenus(Long roleId);

    /**
     * 保存角色菜单
     *
     * @param records
     * @return
     */
    int saveRoleMenus(List<SysRoleMenu> records);

    /**
     * 根据名称查询
     *
     * @param name
     * @return
     */
    List<SysRole> findByName(String name);

    /**
     * 根据Id查询
     *
     * @return
     */
    @Override
    SysRole findById(Long id);
}
