package com.daydream.demo_backup.constants;

import java.io.File;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: 0839d
 * \* Date: 2019/11/19
 * \* Time: 4:08
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
public class BackupConstants {
    /**
     * 备份目录名称
     */
    public static final String BACKUP_FOLDER_NAME = "_database_backup";
    /**
     * 备份目录
     */
    public static final String BACKUP_FOLDER = System.getProperty("user.home") + File.separator + BACKUP_FOLDER_NAME + File.separator;
    /**
     * 日期格式
     */
    public static final String DATE_FORMAT = "yyyy-MM-dd_HHmmss";
    /**
     * SQL拓展名
     */
    public static final String SQL_EXT = ".sql";
    /**
     * 默认备份文件路径
     */
    public static final String DEFAULT_BACKUP_FILE = BACKUP_FOLDER + "default" + SQL_EXT;
    /**
     * 默认文件名
     */
    public static final String DEFAULT_FILE_NAME = "default";

}