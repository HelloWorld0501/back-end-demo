package com.daydream.demo_common.utils;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: 0839d
 * \* Date: 2019/11/3
 * \* Time: 11:08
 * \* To change this template use File | Settings | File Templates.
 * \* Description: 文件操作
 * \
 */
public class FileUtils {
    public static void downloadFile(HttpServletResponse httpServletResponse, File file, String newFileName) {
        try {
            httpServletResponse.setHeader("Content-Disposition", "attachment; filename=" + new String(newFileName.getBytes("ISO-8859-1"), "UTF-8"));
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(httpServletResponse.getOutputStream());
            InputStream inputStream = new FileInputStream(file.getAbsolutePath());
            BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
            int length = 0;
            byte[] temp = new byte[1 * 1024 * 10];
            while ((length = bufferedInputStream.read(temp)) != -1) {
                bufferedOutputStream.write(temp, 0, length);
            }
            bufferedOutputStream.flush();
            bufferedInputStream.close();
            bufferedOutputStream.close();
            inputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 递归删除文件
     *
     * @param file
     */
    public static void deleteFile(File file) {
        // 判断是否是一个目录, 不是的话跳过, 直接删除; 如果是一个目录, 先将其内容清空.
        if (file.isDirectory()) {
            // 获取子文件/目录
            File[] subFiles = file.listFiles();
            // 遍历该目录
            for (File subFile : subFiles) {
                // 递归调用删除该文件: 如果这是一个空目录或文件, 一次递归就可删除.
                // 如果这是一个非空目录, 多次递归清空其内容后再删除
                deleteFile(subFile);
            }
        }
        // 删除空目录或文件
        file.delete();
    }
}