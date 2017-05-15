package com.martin.common.base.utils;

import android.content.Context;
import android.support.annotation.StringRes;
import android.widget.Toast;

/**
 * Toast工具类
 * <p>
 * 非阻塞试显示Toast,防止出现连续点击Toast时的显示问题
 *
 */

public class ToastUtil {


    private static Toast mToast;

    public static void showToast(Context context, CharSequence text, int duration) {
        if (null == mToast) {
            mToast = Toast.makeText(context, text, duration);
        } else {
            mToast.setText(text);
            mToast.setDuration(duration);
        }
        mToast.show();
    }

    public static void showToast(Context context, @StringRes int resId, int duration) {
        if (null == mToast) {
            mToast = Toast.makeText(context, resId, duration);
        } else {
            mToast.setText(resId);
            mToast.setDuration(duration);
        }
        mToast.show();
    }

    public static void showToast(Context context, CharSequence text) {
        showToast(context, text, Toast.LENGTH_SHORT);
    }

    public static void showToast(Context context, @StringRes int resId) {
        showToast(context, resId, Toast.LENGTH_SHORT);
    }

}
