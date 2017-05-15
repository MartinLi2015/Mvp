package com.martin.mvplearning.main;

/**
 *
 */

public class ZhiHuMianPresenter extends ZhiHuContract.ZhihuMainPresenter {
    @Override
    public void onStart() {
        getTabList();
    }

    @Override
    public void getTabList() {
        mView.showTabList(mModel.getTabs());
    }
}
