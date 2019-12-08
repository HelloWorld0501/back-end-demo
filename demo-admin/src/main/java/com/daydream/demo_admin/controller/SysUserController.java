package com.daydream.demo_admin.controller;

import com.daydream.demo_admin.constant.SysConstants;
import com.daydream.demo_admin.model.SysUser;
import com.daydream.demo_admin.service.SysUserService;
import com.daydream.demo_admin.util.PasswordUtils;
import com.daydream.demo_admin.util.ErrorCode;
import com.daydream.demo_common.utils.FileUtils;
import com.daydream.demo_core.http.HttpResult;
import com.daydream.demo_core.page.PageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.List;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: 0839d
 * \* Date: 2019/9/10
 * \* Time: 23:57
 * \* To change this template use File | Settings | File Templates.
 * \* Description: 用户控制器
 * \
 */
@RestController
@RequestMapping("user")
public class SysUserController {
    @Autowired
    private SysUserService sysUserService;

    @GetMapping(value = "/findAll")
    public Object findAll() {
        return sysUserService.findAll();
    }

    @PostMapping(value = "/findPage")
    public HttpResult findPage(@RequestBody PageRequest pageRequest) {
        return HttpResult.ok(sysUserService.findPage(pageRequest));
    }

    @PostMapping(value = "/save")
    public HttpResult save(@RequestBody SysUser record) {
        SysUser user = sysUserService.findById(record.getId());
        if (user != null && SysConstants.ADMIN.equalsIgnoreCase(user.getName())) {
            return HttpResult.error(ErrorCode.NO_EDIT_SUPER_ADMIN);
        }
        if (record.getPassword() != null) {
            String salt = PasswordUtils.getSalt();
            if (user == null) {
                if (sysUserService.findByName(record.getName()) != null) {
                    return HttpResult.error(ErrorCode.USER_NAME_EXITS);
                }
                String password = PasswordUtils.encode(record.getPassword(), salt);
                record.setSalt(salt);
                record.setPassword(password);
            } else {
                //修改用户的同时修改了密码
                if (!record.getPassword().equals(user.getPassword())) {
                    String password = PasswordUtils.encode(record.getPassword(), salt);
                    record.setSalt(salt);
                    record.setPassword(password);
                }
            }
        }
        return HttpResult.ok(sysUserService.save(record));
    }

    @PostMapping(value = "/delete")
    public HttpResult delete(@RequestBody List<SysUser> records) {
        for (SysUser user : records) {
            SysUser sysUser = sysUserService.findById(user.getId());
            if (sysUser != null && SysConstants.ADMIN.equalsIgnoreCase(sysUser.getName())) ;
            {
                return HttpResult.error(ErrorCode.NO_DELETE_SUPER_ADMIN);
            }
        }
        return HttpResult.ok(sysUserService.delete(records));
    }

    @GetMapping(value = "/findByName")
    public HttpResult findByName(@RequestParam String name) {
        return HttpResult.ok(sysUserService.findByName(name));
    }

    @GetMapping(value = "/findPermissions")
    public HttpResult findPermissions(@RequestParam String name) {
        return HttpResult.ok(sysUserService.findPermission(name));
    }

    @GetMapping(value = "/findUserRoles")
    public HttpResult findUserRoles(@RequestParam Long userId) {
        return HttpResult.ok(sysUserService.findUserRoles(userId));
    }

    @PostMapping(value = "/exportExcelUser")
    public void exportExcelUser(@RequestBody PageRequest pageRequest, HttpServletResponse httpServletResponse) {
        File file = sysUserService.createUserExcelFile(pageRequest);
        FileUtils.downloadFile(httpServletResponse, file, file.getName());
    }
}