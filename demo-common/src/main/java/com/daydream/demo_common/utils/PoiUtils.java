package com.daydream.demo_common.utils;

import org.apache.poi.ss.usermodel.Workbook;

import java.io.*;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: 0839d
 * \* Date: 2019/10/25
 * \* Time: 0:16
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
public class PoiUtils {
    /**
     * 生成Excel文件
     *
     * @param workbook
     * @param fileName
     * @return
     */
    public static <WorkBook> File createExcelFile(Workbook workbook, String fileName) {
        OutputStream stream = null;
        File file = null;
        try {
            file = File.createTempFile(fileName, ".xlsx");
            stream = new FileOutputStream(file.getAbsoluteFile());
            workbook.write(stream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(workbook);
            IOUtils.closeQuietly(stream);
        }
        return file;
    }
}