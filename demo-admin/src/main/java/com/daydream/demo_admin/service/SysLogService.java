package com.daydream.demo_admin.service;

import com.daydream.demo_admin.model.SysLog;
import com.daydream.demo_core.page.PageRequest;
import com.daydream.demo_core.page.PageResult;
import com.daydream.demo_core.service.CrudService;

import java.util.List;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: 0839d
 * \* Date: 2019/11/6
 * \* Time: 22:38
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
public interface SysLogService extends CrudService<SysLog> {
    @Override
    int save(SysLog record);

    @Override
    int delete(SysLog record);

    @Override
    int delete(List<SysLog> records);

    @Override
    SysLog findById(Long id);

    @Override
    PageResult findPage(PageRequest pageRequest);
}