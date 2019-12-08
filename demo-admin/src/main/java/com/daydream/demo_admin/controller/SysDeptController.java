package com.daydream.demo_admin.controller;

import com.daydream.demo_admin.model.SysDept;
import com.daydream.demo_admin.service.SysDeptService;
import com.daydream.demo_core.http.HttpResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: 0839d
 * \* Date: 2019/11/5
 * \* Time: 10:43
 * \* To change this template use File | Settings | File Templates.
 * \* Description: 机构控制器
 * \
 */

@RestController
@RequestMapping("dept")
public class SysDeptController {
    @Autowired
    private SysDeptService sysDeptService;

    @PreAuthorize("hasAuthority('sys:dept:add') AND hasAuthority('sys:dept:edit')")
    @PostMapping(value = "/save")
    public HttpResult save(@RequestBody SysDept record) {
        return HttpResult.ok(sysDeptService.save(record));
    }

    @PreAuthorize("hasAuthority('sys:dept:delete')")
    @PostMapping(value = "/delete")
    public HttpResult delete(@RequestBody List<SysDept> records) {
        return HttpResult.ok(sysDeptService.delete(records));
    }

    @PreAuthorize("hasAuthority('sys:dept:view')")
    @GetMapping(value = "/findTree")
    public HttpResult findTree() {
        return HttpResult.ok(sysDeptService.findTree());
    }
}