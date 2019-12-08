package com.daydream.demo_backup.util;

import java.io.File;
import java.io.IOException;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: 0839d
 * \* Date: 2019/11/19
 * \* Time: 1:36
 * \* To change this template use File | Settings | File Templates.
 * \* Description: 数据库备份与还原
 * \
 */
public class MySqlBackupRestoreUtils {
    /**
     * @param host
     * @param userName
     * @param password
     * @param backupFolderPath
     * @param fileName
     * @param database         备份的数据库名
     * @return
     * @throws Exception
     */
    public static boolean backup(String host, String userName, String password, String backupFolderPath, String fileName, String database) throws Exception {
        File backupFolderFile = new File(backupFolderPath);
        if (!backupFolderFile.exists()) {
            backupFolderFile.mkdir();
        }
        System.out.println(backupFolderFile);
        System.out.println(fileName);
        String backupFilePath = backupFolderPath + fileName;
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("mysqldump --opt ")
                .append(" --add-drop-database ")
                .append(" --add-drop-table ")
                .append(" -h")
                .append(host)
                .append(" -u")
                .append(userName)
                .append(" -p")
                .append(password)
                .append(" --result-file=")
                .append(backupFilePath)
                .append(" --default-character-set=utf8 ")
                .append(database);
        System.out.println(stringBuffer.toString());
        Process process = Runtime.getRuntime().exec(getCommand(stringBuffer.toString()));
        if (process.waitFor() == 0) {
            System.out.println("数据已经成功备份到: " + backupFilePath + " 文件!");
            return true;
        }
        return false;
    }

    /**
     * 根据操作系统选择命令
     *
     * @param command
     * @return
     */
    private static String[] getCommand(String command) {
        String os = System.getProperty("os.name");
        String shell = "/bin/bash";
        String c = "-c";
        if (os.toLowerCase().startsWith("win")) {
            shell = "cmd";
            c = "/c";
        }
        String[] cmd = {shell, c, command};
        return cmd;
    }

    /**
     * @param restoreFilePath 数据文件
     * @param host
     * @param userName
     * @param password
     * @param database
     * @return
     * @throws Exception
     */
    public static boolean restore(String restoreFilePath, String host, String userName, String password, String database) throws Exception {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("mysql -h")
                .append(host)
                .append(" -u")
                .append(userName)
                .append(" -p")
                .append(password)
                .append(" ")
                .append(database)
                .append(" < ")
                .append(restoreFilePath);
        System.out.println(stringBuffer.toString());
        try {
            Process process = Runtime.getRuntime().exec(getCommand(stringBuffer.toString()));
            if (process.waitFor() == 0) {
                System.out.println("数据已经从 " + restoreFilePath + " 导入到数据库中");
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}