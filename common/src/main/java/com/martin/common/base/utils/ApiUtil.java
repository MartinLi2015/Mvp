package com.martin.common.base.utils;

import android.os.Build;

/**
 * 判断某个Api版本
 */

public class ApiUtil {

    public static boolean isSupportApi(int targetApi) {
        return Build.VERSION.SDK_INT >= targetApi;
    }
}
