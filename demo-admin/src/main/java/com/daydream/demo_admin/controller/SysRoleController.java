package com.daydream.demo_admin.controller;

import com.daydream.demo_admin.constant.SysConstants;
import com.daydream.demo_admin.model.SysRole;
import com.daydream.demo_admin.model.SysRoleMenu;
import com.daydream.demo_admin.service.SysRoleService;
import com.daydream.demo_admin.util.ErrorCode;
import com.daydream.demo_core.http.HttpResult;
import com.daydream.demo_core.page.PageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: 0839d
 * \* Date: 2019/11/5
 * \* Time: 12:26
 * \* To change this template use File | Settings | File Templates.
 * \* Description: 角色控制器
 * \
 */
@RestController
@RequestMapping("role")
public class SysRoleController {
    @Autowired
    private SysRoleService sysRoleService;

    @PreAuthorize("hasAuthority('sys:role:add') AND hasAuthority('sys:role:edit')")
    @PostMapping(value = "/save")
    public HttpResult save(@RequestBody SysRole record) {
        SysRole role = sysRoleService.findById(record.getId());
        if (role != null && SysConstants.ADMIN.equalsIgnoreCase(role.getName())) {
            return HttpResult.error(ErrorCode.NO_EDIT_SUPER_ADMIN);
        }
        //create new role
        if (record.getId() == null || record.getId() == 0 && !sysRoleService.findByName(record.getName()).isEmpty()) {
            return HttpResult.error(ErrorCode.ROLE_NAME_EXITS);
        }
        return HttpResult.ok(sysRoleService.save(record));
    }

    @PreAuthorize("hasAuthority('sys:role:delete')")
    @PostMapping(value = "/delete")
    public HttpResult delete(@RequestBody List<SysRole> records) {
        return HttpResult.ok(sysRoleService.delete(records));
    }

    @PreAuthorize("hasAuthority('sys:role:view')")
    @PostMapping(value = "/findPage")
    public HttpResult findPage(@RequestBody PageRequest pageRequest) {
        return HttpResult.ok(sysRoleService.findPage(pageRequest));
    }

    @PreAuthorize("hasAuthority('sys:role:view')")
    @GetMapping(value = "/findAll")
    public HttpResult findAll() {
        return HttpResult.ok(sysRoleService.findAll());
    }

    @PreAuthorize("hasAuthority('sys:role:view')")
    @GetMapping(value = "/findRoleMenus")
    public HttpResult findRoleMenus(@RequestParam Long roleId) {
        return HttpResult.ok(sysRoleService.findRoleMenus(roleId));
    }

    @PreAuthorize("hasAuthority('sys:role:view')")
    @PostMapping(value = "/saveRoleMenus")
    public HttpResult saveRoleMenus(@RequestBody List<SysRoleMenu> records) {
        for (SysRoleMenu sysRoleMenu : records) {
            SysRole sysRole = sysRoleService.findById(sysRoleMenu.getId());
            if (SysConstants.ADMIN.equalsIgnoreCase(sysRole.getName())) {
                return HttpResult.error(ErrorCode.NO_EDIT_ADMIN_ROLE_MENU);
            }
        }
        return HttpResult.ok(sysRoleService.saveRoleMenus(records));
    }
}