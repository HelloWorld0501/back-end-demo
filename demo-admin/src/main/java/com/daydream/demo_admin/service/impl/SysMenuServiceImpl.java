package com.daydream.demo_admin.service.impl;

import com.daydream.demo_admin.constant.SysConstants;
import com.daydream.demo_admin.dao.SysMenuMapper;
import com.daydream.demo_admin.model.SysMenu;
import com.daydream.demo_admin.service.SysMenuService;
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
 * \* Date: 2019/10/24
 * \* Time: 22:33
 * \* To change this template use File | Settings | File Templates.
 * \* Description: 菜单管理的实现
 * \
 */
@Service
public class SysMenuServiceImpl implements SysMenuService {

    @Autowired
    private SysMenuMapper sysMenuMapper;

    @Override
    public List<SysMenu> findTree(String userName, int menuType) {
        List<SysMenu> sysMenus = new ArrayList<>();
        List<SysMenu> menus = findByUser(userName);
        for (SysMenu menu : menus) {
            if (menu.getparentId() == null || menu.getparentId() == 0) {
                menu.setLevel(0);
                if (!exists(sysMenus, menu)) {
                    sysMenus.add(menu);
                }
            }
        }
        //比较器compareTo
        sysMenus.sort((o1, o2) -> o1.getOrderNum().compareTo(o2.getOrderNum()));
        findChildren(sysMenus, menus, menuType);
        return sysMenus;
    }

    @Override
    public List<SysMenu> findByUser(String userName) {
        if (userName == null || "".equals(userName) || SysConstants.ADMIN.equalsIgnoreCase(userName)) {
            return sysMenuMapper.findAll();
        }
        return sysMenuMapper.findByUserName(userName);
    }


    @Override
    public int save(SysMenu record) {
        if (record.getId() == null || record.getId() == 0) {
            return sysMenuMapper.insertSelective(record);
        }
        if (record.getparentId() == null) {
            record.setparentId(0L);
        }
        return sysMenuMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public PageResult findPage(PageRequest pageRequest) {
        return MybatisPageHelper.findPage(pageRequest, sysMenuMapper);
    }

    @Override
    public int delete(SysMenu record) {
        return sysMenuMapper.deleteByPrimaryKey(record.getId());
    }

    @Override
    public int delete(List<SysMenu> records) {
        for (SysMenu record : records) {
            delete(record);
        }
        return 1;
    }

    @Override
    public SysMenu findById(Long id) {
        return sysMenuMapper.selectByPrimaryKey(id);
    }

    private boolean exists(List<SysMenu> sysMenus, SysMenu sysMenu) {
        boolean exist = false;
        for (SysMenu menu : sysMenus) {
            if (menu.getId().equals(sysMenu.getId())) {
                exist = true;
                break;
            }
        }
        return exist;
    }

    private void findChildren(List<SysMenu> sysMenus, List<SysMenu> menus, int menuType) {
        for (SysMenu sysMenu : sysMenus) {
            List<SysMenu> children = new ArrayList<>();
            for (SysMenu menu : menus) {
                //数据表中 按钮是类型定义为2
                if (menuType == 1 && menu.getType() == 2) {
                    //获取类型不需要按钮,且菜单类型是按钮的可以过滤掉
                    continue;
                }
                //检查菜单id是否等同于父级id,一级菜单为0
                if (sysMenu.getId() != null && sysMenu.getId().equals(menu.getparentId())) {
                    menu.setParentName(sysMenu.getName());
                    menu.setLevel(sysMenu.getLevel() + 1);
                    if (!exists(children, menu)) {
                        children.add(menu);
                    }
                }
            }
            sysMenu.setChildren(children);
            children.sort((o1, o2) -> o1.getOrderNum().compareTo(o2.getOrderNum()));
            //递归
            findChildren(children, menus, menuType);
        }
    }
}