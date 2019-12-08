package com.daydream.demo_admin.dao;

import com.daydream.demo_admin.model.SysDict;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysDictMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SysDict record);

    int insertSelective(SysDict record);

    SysDict selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysDict record);

    int updateByPrimaryKey(SysDict record);

    /**
     * 分页查询
     *
     * @return
     */
    List<SysDict> findPage();

    /**
     * 按标签名查询
     *
     * @param label
     * @return
     */
    List<SysDict> findByLable(@Param(value = "lable") String label);

    /**
     * 按标签名分页查询
     *
     * @param label
     * @return
     */
    List<SysDict> findPageByLabel(@Param(value = "label") String label);
}