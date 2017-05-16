package com.martin.mvplearning.main;

import com.martin.common.base.utils.RxUtil;
import com.martin.common.data.net.RxService;
import com.martin.mvplearning.api.ZhiHuApi;

import rx.Observable;

/**
 * Created by martin on 2017/5/16.
 */

class DailyModel implements ZhiHuContract.DailyModel {

    @Override
    public Observable<DailyListBean> getDailyData() {
        return RxService.createApi(ZhiHuApi.class).getDailyList().compose(RxUtil.<DailyListBean>rxSchedulerHelper());
    }

    @Override
    public Observable<ZhihuDetailBean> getZhihuDetails(int anInt) {
        return RxService.createApi(ZhiHuApi.class).getDetailInfo(anInt).compose(RxUtil.<ZhihuDetailBean>rxSchedulerHelper());
    }
}
