package com.martin.common.base.utils;

import android.support.annotation.StringRes;
import android.support.design.widget.Snackbar;
import android.view.View;

/**
 * Snackbar
 */

public class SnackbarUtil {

    public static void show(View view, CharSequence text) {
        Snackbar.make(view, text, Snackbar.LENGTH_SHORT).show();
    }

    public static void show(View view, @StringRes int resId) {
        Snackbar.make(view, resId, Snackbar.LENGTH_SHORT).show();
    }

    public static void show(View view, String msg, int duration) {
        Snackbar.make(view, msg, duration).show();
    }

    public static void show(View view, @StringRes int resId, int duration) {
        Snackbar.make(view, resId, duration).show();
    }

}
