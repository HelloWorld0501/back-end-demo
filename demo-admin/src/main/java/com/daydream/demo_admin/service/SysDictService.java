package com.daydream.demo_admin.service;

import com.daydream.demo_admin.model.SysDict;
import com.daydream.demo_core.service.CrudService;

import java.util.List;

public interface SysDictService extends CrudService<SysDict> {
    /**
     * 根据名称查询
     *
     * @param lable
     * @return
     */
    List<SysDict> findByLable(String lable);
}
