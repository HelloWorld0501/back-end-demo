package com.daydream.demo_admin.controller;

import com.daydream.demo_admin.model.SysLog;
import com.daydream.demo_admin.service.SysLogService;
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
 * \* Time: 22:51
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
@RestController
@RequestMapping("log")
public class SysLogController {
    @Autowired
    private SysLogService sysLogService;

    @PreAuthorize("hasAuthority('sys:log:view')")
    @PostMapping(value = "findPage")
    public HttpResult findPage(@RequestBody PageRequest pageRequest) {
        return HttpResult.ok(sysLogService.findPage(pageRequest));
    }

    @PreAuthorize("hasAuthority('sys:log:delete')")
    @PostMapping(value = "/delete")
    public HttpResult delete(@RequestBody List<SysLog> records) {
        return HttpResult.ok(sysLogService.delete(records));
    }
}