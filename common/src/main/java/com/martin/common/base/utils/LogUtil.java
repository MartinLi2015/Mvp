package com.martin.common.base.utils;

import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;

/**
 * 日志工具
 */

public class LogUtil {


    static final String TAG = "LogUtil";
    private static boolean isDebug = true;

    public static boolean isDebug() {
        return isDebug;
    }

    public static void setDebug(boolean isDebug) {
        LogUtil.isDebug = isDebug;
    }

    public static String getTAG() {
        return TAG;
    }


    public static void e(String msg) {
        if (isDebug) {
            Log.e(TAG, msg);
        }
    }

    public static void w(String msg) {
        if (isDebug) {
            Log.w(TAG, msg);
        }
    }

    public static void i(String msg) {
        if (isDebug) {
            Log.i(TAG, msg);
        }
    }

    public static void v(String msg) {
        if (isDebug) {
            Log.v(TAG, msg);
        }
    }

    public static void d(String msg) {
        if (isDebug) {
            Log.d(TAG, msg);
        }
    }

    //for warming log
    public static void w(String tag, String msg) {
        if (isDebug) {
            tag = initTag(tag);
            Log.w(tag, msg);
        }
    }

    //for info log
    public static void i(String tag, String msg) {
        if (isDebug) {
            tag = initTag(tag);
            Log.i(tag, msg);
        }
    }

    //for debug log
    public static void d(String tag, String msg) {
        if (isDebug) {
            tag = initTag(tag);
            Log.d(tag, msg);
        }
    }

    //for verbose log
    public static void v(String tag, String msg) {
        if (isDebug) {
            tag = initTag(tag);
            Log.v(tag, msg);
        }
    }

    //for verbose log
    public static void e(String tag, String msg) {
        if (isDebug) {
            tag = initTag(tag);
            Log.e(tag, msg);
        }
    }

    @NonNull
    private static String initTag(String tag) {
        if (TextUtils.isEmpty(tag)) {
            return TAG;
        }
        return tag;
    }

    /**
     * 点击Log跳转到指定源码位置
     *
     * @param tag
     * @param msg
     */
    public static void showLog(String tag, String msg) {
        if (isDebug) {
            tag = initTag(tag);

            StackTraceElement[] stackTraceElement = Thread.currentThread().getStackTrace();

            int currentIndex = -1;
            for (int i = 0, len = stackTraceElement.length; i < len; i++) {
                if (stackTraceElement[i].getMethodName().compareTo("showLog") == 0) {
                    currentIndex = i + 1;
                    break;
                }
            }

            if (currentIndex > 0) {
                String fullClassName = stackTraceElement[currentIndex].getClassName();
                String className = fullClassName.substring(fullClassName.lastIndexOf("." + 1));
                String methodName = stackTraceElement[currentIndex].getMethodName();
                String lineNumber = String.valueOf(stackTraceElement[currentIndex].getLineNumber());
                Log.i(tag, msg + "\n  ---->at " + className + "." + methodName + "("
                        + className + ".java:" + lineNumber + ")");
            } else {
                Log.i(tag, msg);
            }

        }
    }


}
