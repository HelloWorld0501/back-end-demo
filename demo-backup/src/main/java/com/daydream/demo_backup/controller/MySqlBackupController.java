package com.daydream.demo_backup.controller;

import com.daydream.demo_backup.constants.BackupConstants;
import com.daydream.demo_backup.datasource.BackupDataSourceProperties;
import com.daydream.demo_backup.service.MysqlBackupService;
import com.daydream.demo_backup.util.HttpResult;
import com.daydream.demo_common.utils.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: 0839d
 * \* Date: 2019/11/19
 * \* Time: 3:13
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
@RestController
@RequestMapping("/backup")
public class MySqlBackupController {
    @Autowired
    MysqlBackupService mysqlBackupService;
    @Autowired
    BackupDataSourceProperties dataSourceProperties;

    @GetMapping("/backup")
    public HttpResult backup() {
        //根据时间生成文件名
        return backup(new SimpleDateFormat(BackupConstants.DATE_FORMAT).format(new Date()) + BackupConstants.SQL_EXT);
    }

    private HttpResult backup(String fileName) {
        try {
            boolean success = mysqlBackupService.backup(dataSourceProperties.getHost(), dataSourceProperties.getUserName(), dataSourceProperties.getPassword(), BackupConstants.BACKUP_FOLDER, fileName, dataSourceProperties.getDatabase());
            if (!success) {
                HttpResult.error("数据备份失败!");
            }
        } catch (Exception e) {
            return HttpResult.error(500, e.getMessage());
        }
        return HttpResult.ok();
    }

    @GetMapping("/findRecords")
    public HttpResult findBackupRecords() {
        if (!new File(BackupConstants.DEFAULT_BACKUP_FILE).exists()) {
            backup(BackupConstants.DEFAULT_FILE_NAME + BackupConstants.SQL_EXT);
        }
        List<Map<String, String>> backupRecords = new ArrayList<>();
        File restoreFolderFile = new File(BackupConstants.BACKUP_FOLDER);
        if (restoreFolderFile.exists()) {
            for (File file : restoreFolderFile.listFiles()) {
                Map<String, String> backup = new HashMap<>();
                backup.put("name", file.getName());
                backup.put("title", file.getName());
                if (BackupConstants.DEFAULT_BACKUP_FILE.equalsIgnoreCase(file.getName())) {
                    backup.put("title", "系统默认备份");
                }
                backupRecords.add(backup);
            }
        }
        //除默认备份外,新备份在前面
        backupRecords.sort((o1, o2) -> BackupConstants.DEFAULT_BACKUP_FILE.equalsIgnoreCase(o1.get("name")) ? -1 : BackupConstants.DEFAULT_BACKUP_FILE.equalsIgnoreCase(o2.get("name")) ? 1 : o2.get("name").compareTo(o1.get("name")));
        return HttpResult.ok(backupRecords);
    }

    @GetMapping("delete")
    public HttpResult deleteBackupRecord(@RequestParam String name) {
        System.out.println(name);
        if (BackupConstants.DEFAULT_FILE_NAME.equals(name)) {
            return HttpResult.error("禁止删除默认备份");
        }
        String restoreFilePath = BackupConstants.BACKUP_FOLDER + name + BackupConstants.SQL_EXT;
        System.out.println(restoreFilePath);
        try {
            FileUtils.deleteFile(new File(restoreFilePath));
        } catch (Exception e) {
            return HttpResult.error(500, e.getMessage());
        }
        return HttpResult.ok();
    }

    @GetMapping("/restore")
    public HttpResult restore(@RequestParam String name) throws IOException {
        try {
            mysqlBackupService.restore(BackupConstants.BACKUP_FOLDER + name + BackupConstants.SQL_EXT, dataSourceProperties.getHost(), dataSourceProperties.getUserName(), dataSourceProperties.getPassword(), dataSourceProperties.getDatabase());
        } catch (Exception e) {
            return HttpResult.error(500, e.getMessage());
        }
        return HttpResult.ok();
    }
}