package com.daydream.demo_admin.service.impl;

import com.daydream.demo_admin.dao.SysLoginLogMapper;
import com.daydream.demo_admin.model.SysLoginLog;
import com.daydream.demo_admin.service.SysLoginLogService;
import com.daydream.demo_core.page.MybatisPageHelper;
import com.daydream.demo_core.page.PageRequest;
import com.daydream.demo_core.page.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: 0839d
 * \* Date: 2019/11/6
 * \* Time: 20:21
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
@Service
public class SysLoginLogServiceImpl implements SysLoginLogService {

    @Autowired
    private SysLoginLogMapper sysLoginLogMapper;

    @Override
    public int save(SysLoginLog record) {
        if (record.getId() == null || record.getId() == 0) {
            return sysLoginLogMapper.insertSelective(record);
        }
        return sysLoginLogMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int delete(SysLoginLog record) {
        return sysLoginLogMapper.deleteByPrimaryKey(record.getId());
    }

    @Override
    public int delete(List<SysLoginLog> records) {
        for (SysLoginLog sysLoginLog : records) {
            delete(sysLoginLog);
        }
        return 1;
    }

    @Override
    public SysLoginLog findById(Long id) {
        return sysLoginLogMapper.selectByPrimaryKey(id);
    }

    @Override
    public PageResult findPage(PageRequest pageRequest) {
        Object userName = pageRequest.getParam("userName");
        if (userName != null) {
            return MybatisPageHelper.findPage(pageRequest, sysLoginLogMapper, "findPageByUserName", userName);
        }
        Object status = pageRequest.getParam("status");
        if (status != null) {
            return MybatisPageHelper.findPage(pageRequest, sysLoginLogMapper, "findPageStatus", status);
        }
        return MybatisPageHelper.findPage(pageRequest, sysLoginLogMapper);
    }
}