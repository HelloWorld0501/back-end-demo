package com.daydream.demo_admin.service.impl;

import com.daydream.demo_admin.dao.SysDeptMapper;
import com.daydream.demo_admin.model.SysDept;
import com.daydream.demo_admin.service.SysDeptService;
import com.daydream.demo_core.page.MybatisPageHelper;
import com.daydream.demo_core.page.PageRequest;
import com.daydream.demo_core.page.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: 0839d
 * \* Date: 2019/11/5
 * \* Time: 10:09
 * \* To change this template use File | Settings | File Templates.
 * \* Description: 机构管理方法实现
 * \
 */
@Service
public class SysDeptServiceImpl implements SysDeptService {

    @Autowired
    private SysDeptMapper sysDeptMapper;

    @Override
    public List<SysDept> findTree() {
        List<SysDept> sysDepts = new ArrayList<>();
        List<SysDept> depts = sysDeptMapper.findAll();
        for (SysDept dept : depts) {
            if (dept.getParendId() == null || dept.getParendId() == 0) {
                dept.setLevel(0);
                depts.add(dept);
            }
        }
        findChildren(sysDepts, depts);
        return sysDepts;
    }

    private void findChildren(List<SysDept> sysDepts, List<SysDept> depts) {
        for (SysDept sysDept : sysDepts) {
            List<SysDept> children = new ArrayList<>();
            for (SysDept dept : depts) {
                if (sysDept.getId() != null && sysDept.getId().equals(dept.getParendId())) {
                    dept.setParentName(dept.getName());
                    dept.setLevel(sysDept.getLevel() + 1);
                    children.add(dept);
                }
            }
            sysDept.setChildren(children);
            //递归遍历
            findChildren(children, depts);
        }
    }

    @Override
    public int save(SysDept record) {
        if (record.getId() == null || record.getId() == 0) {
            return sysDeptMapper.insertSelective(record);
        }
        return sysDeptMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int delete(SysDept record) {
        return sysDeptMapper.deleteByPrimaryKey(record.getId());
    }

    @Override
    public int delete(List<SysDept> records) {
        for (SysDept sysDept : records) {
            delete(sysDept);
        }
        return 1;
    }

    @Override
    public SysDept findById(Long id) {
        return sysDeptMapper.selectByPrimaryKey(id);
    }

    @Override
    public PageResult findPage(PageRequest pageRequest) {
        return MybatisPageHelper.findPage(pageRequest, sysDeptMapper);
    }
}