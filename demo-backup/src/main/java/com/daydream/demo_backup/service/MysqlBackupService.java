package com.daydream.demo_backup.service;

public interface MysqlBackupService {
    /**
     * @param host
     * @param userName
     * @param password
     * @param backupFolderPath 备份文件路径
     * @param fileName         备份文件名
     * @param database         数据库名
     * @return
     * @throws Exception
     */
    boolean backup(String host, String userName, String password, String backupFolderPath, String fileName, String database) throws Exception;

    /**
     * @param restoreFilePath 数据库备份脚本路径
     * @param host
     * @param userName
     * @param password
     * @param database
     * @return
     * @throws Exception
     */
    boolean restore(String restoreFilePath, String host, String userName, String password, String database) throws Exception;
}
