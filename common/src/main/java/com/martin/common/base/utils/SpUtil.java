package com.martin.common.base.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.martin.common.base.BaseActivity;

/**
 * Sp工具类
 * @author martin
 */

public class SpUtil {

    static SharedPreferences prefs;

    /**
     * 初始化数据
     *
     * @param context
     */
    public static void init(Context context) {
        prefs = PreferenceManager.getDefaultSharedPreferences(context);
    }

    /**
     * 获取主题
     * @return true表示夜间主题
     */
    public static boolean isNight() {
        return prefs.getBoolean("isNight", false);
    }

    /**
     *  保存当前主题
     * @param context
     * @param isNight
     */
    public static void setNight(Context context, boolean isNight) {
        prefs.edit().putBoolean("isNight", isNight).apply();
        if (context instanceof BaseActivity) {
            ((BaseActivity) context).reload();
        }
    }

}
