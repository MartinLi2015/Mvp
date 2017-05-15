package com.martin.common.base;

import java.util.HashMap;
import java.util.Map;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action;
import rx.functions.Action1;
import rx.subscriptions.CompositeSubscription;

/**
 * 用于管理RxBus的事件和Rxjava相关代码的生命周期处理
 * @author martin
 */

public class RxManager {

    public RxBus mRxBus = RxBus.getInstance();
    private Map<String, Observable<?>> mObservables = new HashMap<>();
    private CompositeSubscription mCompositeSubscription = new CompositeSubscription();

    public void on(String eventName, Action1<Object> action1){

        Observable<?> observable = mRxBus.register(eventName);
        mObservables.put(eventName,observable);
        mCompositeSubscription.add(observable.observeOn(AndroidSchedulers.mainThread()).subscribe(action1, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                throwable.printStackTrace();
            }
        }));
    }
    public void add(Subscription m){
        mCompositeSubscription.add(m);
    }

    public void clear(){
        mCompositeSubscription.unsubscribe();
        for (Map.Entry<String,Observable<?>> entry:mObservables.entrySet()){
            mRxBus.unRegister(entry.getKey(),entry.getValue());
        }
    }
    public void post(Object tag,Object content){
        mRxBus.post(tag,content);
    }
}
