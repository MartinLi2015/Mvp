package com.martin.mvplearning.main;

import com.martin.common.base.utils.RxUtil;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.functions.Action1;

/**
 * Created by martin on 2017/5/16.
 */

class DailyPresenter extends ZhiHuContract.DailyPresenter {
    private int topCount = 0;
    private int currentTopCount = 0;

    @Override
    public void onStart() {

    }

    @Override
    public void getDailyData() {
        mRxManager.add(mModel.getDailyData().subscribe(new Action1<DailyListBean>() {
            @Override
            public void call(DailyListBean bean) {
                mView.showContent(bean);
                topCount = bean.getTop_stories().size();
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                mView.showError("数据加载失败ヽ(≧Д≦)ノ");
            }
        }));

    }

    @Override
    public void startInterval() {
        mRxManager.add(Observable.interval(5, TimeUnit.SECONDS)
                .compose(RxUtil.<Long>rxSchedulerHelper())
        .subscribe(new Action1<Long>() {
            @Override
            public void call(Long aLong) {
                if(currentTopCount == topCount){
                    currentTopCount = 0;
                }
                mView.doInterval(currentTopCount ++ );
            }
        }));
    }
}
