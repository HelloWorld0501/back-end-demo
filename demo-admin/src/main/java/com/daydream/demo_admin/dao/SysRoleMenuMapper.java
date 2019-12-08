package com.daydream.demo_admin.dao;

import com.daydream.demo_admin.model.SysRoleMenu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysRoleMenuMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SysRoleMenu record);

    int insertSelective(SysRoleMenu record);

    SysRoleMenu selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysRoleMenu record);

    int updateByPrimaryKeyWithBLOBs(SysRoleMenu record);

    int updateByPrimaryKey(SysRoleMenu record);

    List<SysRoleMenu> findRoleMenus(@Param(value = "roleId") Long roleId);

    List<SysRoleMenu> findAll();

    int deleteByRoleId(@Param(value = "roleId") Long roleId);
}