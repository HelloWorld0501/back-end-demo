package com.daydream.demo_admin.service.impl;

import com.daydream.demo_admin.dao.SysLogMapper;
import com.daydream.demo_admin.model.SysLog;
import com.daydream.demo_admin.service.SysLogService;
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
 * \* Time: 22:39
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
@Service
public class SysLogServiceImpl implements SysLogService {
    @Autowired
    private SysLogMapper sysLogMapper;

    @Override
    public int save(SysLog record) {
        if (record.getId() == null || record.getId() == 0) {
            return sysLogMapper.insertSelective(record);
        }
        return sysLogMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int delete(SysLog record) {
        return sysLogMapper.deleteByPrimaryKey(record.getId());
    }

    @Override
    public int delete(List<SysLog> records) {
        for (SysLog sysLog : records) {
            delete(sysLog);
        }
        return 1;
    }

    @Override
    public SysLog findById(Long id) {
        return sysLogMapper.selectByPrimaryKey(id);
    }

    @Override
    public PageResult findPage(PageRequest pageRequest) {
        Object label = pageRequest.getParam("userName");
        if (label != null) {
            return MybatisPageHelper.findPage(pageRequest, sysLogMapper, "findPageByUserName", label);
        }
        return MybatisPageHelper.findPage(pageRequest, sysLogMapper);
    }
}