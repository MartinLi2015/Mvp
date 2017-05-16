package com.martin.mvplearning.main;

import com.martin.common.base.BaseModel;
import com.martin.common.base.BasePresenter;
import com.martin.common.base.BaseView;

import rx.Observable;

/**
 * Created by martin on 2017/5/16.
 */

public interface ZhiHuContract {


    interface ZhihuMainModel extends BaseModel {
        String[] getTabs();
    }

    interface ZhihuMainView extends BaseView {
        void showTabList(String[] mTabs);
    }

    abstract class ZhihuMainPresenter extends BasePresenter<ZhihuMainModel, ZhihuMainView> {
        public abstract void getTabList();
    }


    interface DailyView extends BaseView {
        void showContent(DailyListBean bean);
        void doInterval(int i);
    }
    interface DailyModel extends BaseModel{
        Observable<DailyListBean> getDailyData();
        Observable<ZhihuDetailBean> getZhihuDetails(int anInt);
    }

    abstract class DailyPresenter extends BasePresenter<DailyModel,DailyView>{
        public abstract void getDailyData();
        public abstract void startInterval();
    }


}
