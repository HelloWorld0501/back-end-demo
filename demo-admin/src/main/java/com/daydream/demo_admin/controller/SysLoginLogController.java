package com.daydream.demo_admin.controller;

import com.daydream.demo_admin.model.SysLoginLog;
import com.daydream.demo_admin.service.SysLoginLogService;
import com.daydream.demo_core.http.HttpResult;
import com.daydream.demo_core.page.PageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: 0839d
 * \* Date: 2019/11/6
 * \* Time: 20:44
 * \* To change this template use File | Settings | File Templates.
 * \* Description: 登录日志控制器
 * \
 */

@RestController
@RequestMapping("loginlog")
public class SysLoginLogController {
    @Autowired
    private SysLoginLogService sysLoginLogService;

    @PreAuthorize("hasAuthority('sys:loginlog:view')")
    @PostMapping(value = "/findPage")
    public HttpResult findPage(@RequestBody PageRequest pageRequest) {
        return HttpResult.ok(sysLoginLogService.findPage(pageRequest));
    }

    @PreAuthorize("hasAuthority('sys:loginlog:delete')")
    @PostMapping(value = "/delete")
    public HttpResult delete(@RequestBody List<SysLoginLog> records) {
        return HttpResult.ok(sysLoginLogService.delete(records));
    }
}