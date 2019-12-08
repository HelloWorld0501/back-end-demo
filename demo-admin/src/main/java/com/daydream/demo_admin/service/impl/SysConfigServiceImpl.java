package com.daydream.demo_admin.service.impl;

import com.daydream.demo_admin.dao.SysConfigMapper;
import com.daydream.demo_admin.model.SysConfig;
import com.daydream.demo_admin.service.SysConfigService;
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
 * \* Time: 17:41
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
@Service
public class SysConfigServiceImpl implements SysConfigService {

    @Autowired
    private SysConfigMapper sysConfigMapper;

    @Override
    public int save(SysConfig record) {
        if (record.getId() == null || record.getId() == 0) {
            return sysConfigMapper.insertSelective(record);
        }
        return sysConfigMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int delete(SysConfig record) {
        return sysConfigMapper.deleteByPrimaryKey(record.getId());
    }

    @Override
    public int delete(List<SysConfig> records) {
        for (SysConfig sysConfig : records) {
            delete(sysConfig);
        }
        return 1;
    }

    @Override
    public SysConfig findById(Long id) {
        return sysConfigMapper.selectByPrimaryKey(id);
    }

    @Override
    public PageResult findPage(PageRequest pageRequest) {
        Object label = pageRequest.getParam("label");
        if (label != null) {
            return MybatisPageHelper.findPage(pageRequest, sysConfigMapper, "findPageByLabel", label);
        }
        return MybatisPageHelper.findPage(pageRequest, sysConfigMapper);
    }


    public List<SysConfig> findByLable(String label) {
        return sysConfigMapper.findByLabel(label);

    }
}