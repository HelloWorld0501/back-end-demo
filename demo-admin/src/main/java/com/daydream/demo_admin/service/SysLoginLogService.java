package com.daydream.demo_admin.service;

import com.daydream.demo_admin.model.SysLoginLog;
import com.daydream.demo_core.page.PageRequest;
import com.daydream.demo_core.page.PageResult;
import com.daydream.demo_core.service.CrudService;

import java.util.List;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: 0839d
 * \* Date: 2019/11/6
 * \* Time: 20:17
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
public interface SysLoginLogService extends CrudService<SysLoginLog> {
    @Override
    int save(SysLoginLog record);

    @Override
    int delete(SysLoginLog record);

    @Override
    int delete(List<SysLoginLog> records);

    @Override
    SysLoginLog findById(Long id);

    @Override
    PageResult findPage(PageRequest pageRequest);
}