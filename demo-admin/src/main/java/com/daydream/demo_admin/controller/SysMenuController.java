package com.daydream.demo_admin.controller;

import com.daydream.demo_admin.model.SysMenu;
import com.daydream.demo_admin.service.SysMenuService;
import com.daydream.demo_core.http.HttpResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: 0839d
 * \* Date: 2019/11/6
 * \* Time: 22:14
 * \* To change this template use File | Settings | File Templates.
 * \* Description:菜单控制器
 * \
 */
@RestController
@RequestMapping("menu")
public class SysMenuController {
    @Autowired
    private SysMenuService sysMenuService;

    @PreAuthorize("hasAuthority('sys:menu:add') AND hasAuthority('sys:menu:edit')")
    @PostMapping(value = "/save")
    public HttpResult save(@RequestBody SysMenu record) {
        return HttpResult.ok(sysMenuService.save(record));
    }

    @PreAuthorize("hasAuthority('sys:menu:delete')")
    @PostMapping(value = "delete")
    public HttpResult delete(@RequestBody List<SysMenu> records) {
        return HttpResult.ok(sysMenuService.delete(records));
    }

    @PreAuthorize("hasAuthority('sys:menu:view')")
    @GetMapping(value = "/findNavTree")
    public HttpResult findNavTree(@RequestParam String userName) {
        return HttpResult.ok(sysMenuService.findTree(userName, 1));
    }

    @PreAuthorize("hasAuthority('sys:menu:view')")
    @GetMapping(value = "/findMenuTree")
    public HttpResult findMenuTree() {
        return HttpResult.ok(sysMenuService.findTree(null, 0));
    }
}