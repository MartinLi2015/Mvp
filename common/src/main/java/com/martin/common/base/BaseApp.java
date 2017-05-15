package com.martin.common.base;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;

import com.martin.common.base.utils.SharePrefUtils;
import com.martin.common.base.utils.SpUtil;

/**
 * 基类Application
 */

public abstract class BaseApp extends Application {


    private static BaseApp app;

    public static synchronized BaseApp getInstance() {
        return app;
    }

    public static Context getAppContext() {
        return app.getApplicationContext();
    }

    public static Resources getAppRescources() {
        return app.getResources();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        SharePrefUtils.init(this);
        SpUtil.init(this);
    }

    public abstract String setBaseUrl();

}
