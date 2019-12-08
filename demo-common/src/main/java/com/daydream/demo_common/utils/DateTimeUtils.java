package com.daydream.demo_common.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: 0839d
 * \* Date: 2019/10/25
 * \* Time: 4:53
 * \* To change this template use File | Settings | File Templates.
 * \* Description: 时间工具
 * \
 */
public class DateTimeUtils {
    public static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    /**
     * 格式化时间
     *
     * @return
     */
    public static String getDateTime(Date date) {
        return (new SimpleDateFormat(DATE_FORMAT)).format(new Date());
    }

    /**
     * 获取格式化时间
     *
     * @return
     */
    public static String getDateTime() {
        return getDateTime(new Date());
    }
}