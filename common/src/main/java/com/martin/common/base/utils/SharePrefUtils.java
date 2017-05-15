package com.martin.common.base.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.martin.common.base.CoreConstants;

/**
 * SharedPreferences工具类
 */

public class SharePrefUtils {

    private static final boolean DEFAULT_NO_IMAGE = false;
    private static final boolean DEFAULT_AUTO_SAVE = true;
    static SharedPreferences prefs;

    public static void init(Context context) {
        prefs = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static int getThemeIndex() {
        return prefs.getInt("ThemeIndex", 9);
    }

    public static void setThemeIndex(int index) {
        prefs.edit().putInt("ThemeIndex", index).apply();
    }

    public static boolean getNightModel() {
        return prefs.getBoolean("pNightMode", false);
    }

    public static void setNightModel(boolean nightModel) {
        prefs.edit().putBoolean("pNightMode", nightModel).apply();
    }

    public static boolean getNoImageState() {
        return prefs.getBoolean(CoreConstants.SP_NO_IMAGE, DEFAULT_NO_IMAGE);
    }

    public static void setNoImageState(boolean state) {
        prefs.edit().putBoolean(CoreConstants.SP_NO_IMAGE, state).apply();
    }

    public static boolean getAutoCacheState() {
        return prefs.getBoolean(CoreConstants.SP_AUTO_CACHE, DEFAULT_AUTO_SAVE);
    }

    public static void setAutoCacheState(boolean state) {
        prefs.edit().putBoolean(CoreConstants.SP_AUTO_CACHE, state).apply();
    }

}
