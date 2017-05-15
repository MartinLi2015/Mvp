package com.martin.common.data.net;

import com.martin.common.base.BaseApp;

import java.io.File;

import okhttp3.Cache;

/**
 * Created by martin on 2017/5/5.
 */

public class HttpCache {

    private static final int HTTP_RESPONSE_DISK_CACHE_MAX_SIZE = 50*1024*1024;
    public static Cache getCache(){
        return new Cache(new File(BaseApp.getAppContext().getCacheDir().getAbsolutePath()+File.separator+ "data/NetCache")
                ,HTTP_RESPONSE_DISK_CACHE_MAX_SIZE);
    }
}
