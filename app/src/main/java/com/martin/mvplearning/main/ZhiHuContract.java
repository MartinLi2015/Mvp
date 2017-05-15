package com.martin.mvplearning.main;

import com.martin.common.base.BaseModel;
import com.martin.common.base.BasePresenter;
import com.martin.common.base.BaseView;

/**
 * Created by martin on 2017/5/16.
 */

public interface ZhiHuContract {


    abstract class ZhihuMainPresenter extends BasePresenter<ZhihuMainModel,ZhihuMainView>{
        public abstract void getTabList();
    }
    interface ZhihuMainModel extends BaseModel{
        String[] getTabs();
    }
    interface ZhihuMainView extends BaseView{
        void showTabList(String[] mTabs);
    }


}
