package com.daydream.demo_admin.dao;

import com.daydream.demo_admin.model.SysUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysUserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SysUser record);

    int insertSelective(SysUser record);

    SysUser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysUser record);

    int updateByPrimaryKey(SysUser record);


    /**
     * @return
     * @description 查找所有用户
     */
    List<SysUser> findAll();

    /**
     * 分页查询
     *
     * @return
     */
    List<SysUser> findPage();

    /**
     * 按用户名查找
     *
     * @param name 用户名
     * @return
     */
    SysUser findByName(@Param(value = "name") String name);

    List<SysUser> findPageByName(@Param(value = "name") String name);

    List<SysUser> findPageByNameAndEmail(@Param(value = "name") String name, @Param(value = "email") String email);
}