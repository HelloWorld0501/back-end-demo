package com.daydream.demo_admin.service;

import com.daydream.demo_admin.model.SysConfig;
import com.daydream.demo_core.service.CrudService;

import java.util.List;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: 0839d
 * \* Date: 2019/11/6
 * \* Time: 17:39
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
public interface SysConfigService extends CrudService<SysConfig> {
    /**
     * 根据名称查询
     *
     * @return
     */
    List<SysConfig> findByLable(String label);
}