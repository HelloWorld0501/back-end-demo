package com.daydream.demo_admin.service;

import com.daydream.demo_admin.model.SysDept;
import com.daydream.demo_core.service.CrudService;

import java.util.List;

public interface SysDeptService extends CrudService<SysDept> {
    /**
     * 查找机构树
     *
     * @return
     */
    List<SysDept> findTree();
}