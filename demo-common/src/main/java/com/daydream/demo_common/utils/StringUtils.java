package com.daydream.demo_common.utils;

public class StringUtils {
    /**
     * 判断前端的字符串是否是空串
     *
     * @param s
     * @return
     */
    public static boolean isBlank(String s) {
        return s == null || "".equals(s) || "null".equals(s) || "undefined".equals(s);
    }
}
