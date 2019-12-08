package com.daydream.demo_admin.controller;

import com.daydream.demo_admin.model.SysDict;
import com.daydream.demo_admin.service.SysDictService;
import com.daydream.demo_core.http.HttpResult;
import com.daydream.demo_core.page.PageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: 0839d
 * \* Date: 2019/9/24
 * \* Time: 0:03
 * \* To change this template use File | Settings | File Templates.
 * \* Description: 字典控制器
 * \
 */
@RestController
@RequestMapping("dict")
public class SysDictController {
    @Autowired
    private SysDictService sysDictService;

    @PreAuthorize("hasAuthority('sys:dict:add') AND hasAuthority('sys:dict:edit')")
    public HttpResult save(@RequestBody SysDict sysDict) {
        return HttpResult.ok(sysDictService.save(sysDict));
    }

    @PreAuthorize("hasAuthority('sys:dict:delete')")
    @PostMapping(value = "/delete")
    public HttpResult delete(@RequestBody List<SysDict> sysDicts) {
        return HttpResult.ok(sysDictService.delete(sysDicts));
    }

    @PreAuthorize("hasAuthority('sys:dict:view')")
    @PostMapping(value = "/findPage")
    public HttpResult findPage(@RequestBody PageRequest pageRequest) {
        return HttpResult.ok(sysDictService.findPage(pageRequest));
    }

    @PreAuthorize("hasAuthority('sys:dict:view')")
    @PostMapping(value = "/findByLable")
    public HttpResult findByLable(@RequestParam String lable) {
        return HttpResult.ok(sysDictService.findByLable(lable));
    }
}