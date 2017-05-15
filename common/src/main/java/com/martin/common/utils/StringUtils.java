package com.martin.common.utils;

/**
 * Created by martin on 2017/5/6.
 */

public class StringUtils {

    public static boolean isEmpty(CharSequence str) {
        return "null".equals(str) || str == null || str.length() == 0;

    }
}
