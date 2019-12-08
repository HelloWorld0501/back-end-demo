package com.daydream.demo_common.utils;

import java.io.Closeable;
import java.io.IOException;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: 0839d
 * \* Date: 2019/10/25
 * \* Time: 0:31
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
public class IOUtils {
    public static void closeQuietly(final Closeable closeable) {
        try {
            if (closeable != null) {
                closeable.close();
            }
        } catch (final IOException e) {
            //啥也不干
        }
    }
}