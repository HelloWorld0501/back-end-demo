package com.daydream.demo_admin.dao;

import com.daydream.demo_admin.model.SysRoleDept;

public interface SysRoleDeptMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SysRoleDept record);

    int insertSelective(SysRoleDept record);

    SysRoleDept selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysRoleDept record);

    int updateByPrimaryKeyWithBLOBs(SysRoleDept record);

    int updateByPrimaryKey(SysRoleDept record);
}