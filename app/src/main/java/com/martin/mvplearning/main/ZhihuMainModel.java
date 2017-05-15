package com.martin.mvplearning.main;

/**
 * Created by martin on 2017/5/16.
 */

public class ZhihuMainModel implements ZhiHuContract.ZhihuMainModel {
    @Override
    public String[] getTabs() {
        String[] mTabs = new String[]{"日报", "专栏", "微信", "热门"};
        return mTabs;
    }
}
