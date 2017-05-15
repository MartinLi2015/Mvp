package com.martin.common.base;


import android.support.annotation.NonNull;

import com.martin.common.base.utils.LogUtil;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action;
import rx.functions.Action1;
import rx.subjects.PublishSubject;
import rx.subjects.Subject;

/**
 * 用Rxjava 实现EventBus
 *
 * @author martin
 */

public class RxBus {

    private static class RxBusHolder {
        private static RxBus instance = new RxBus();
    }

    public static RxBus getInstance() {
        return RxBusHolder.instance;
    }

    private ConcurrentHashMap<Object, List<Subject>> subjectMapper = new ConcurrentHashMap<>();

    public RxBus onEvent(Observable<?> observable, Action1<Object> action1) {
        observable.observeOn(AndroidSchedulers.mainThread()).subscribe(action1, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                throwable.printStackTrace();
            }
        });
        return getInstance();
    }

    public <T> Observable<T> register(@NonNull Object tag) {
        List<Subject> subjectList = subjectMapper.get(tag);
        if (null == subjectList) {
            subjectList = new ArrayList<>();
            subjectMapper.put(tag, subjectList);
        }

        Subject<T, T> subject;
        subjectList.add(subject = PublishSubject.create());
        LogUtil.d("register", tag + "  size:" + subjectList.size());
        return subject;
    }

    public RxBus unRegister(@NonNull String tag, @NonNull Observable<?> observable) {

        if (null == observable) {
            return getInstance();
        }
        List<Subject> subjects = subjectMapper.get(tag);

        if (null != subjects) {
            subjects.remove(observable);
            if (isEmpty(subjects)) {
                subjectMapper.remove(tag);
                LogUtil.d("unregister", tag + "  size:" + subjects.size());
            }
        }
        return getInstance();
    }

    private boolean isEmpty(Collection<Subject> subjects) {
        return null == subjects || subjects.isEmpty();
    }

    public void post(@NonNull Object content) {
        post(content.getClass().getName(), content);
    }

    public void post(@NonNull Object tag, @NonNull Object content) {
        LogUtil.d("post", "eventName: " + tag);
        List<Subject> subjectList = subjectMapper.get(tag);
        if (!isEmpty(subjectList)) {
            for (Subject subject : subjectList) {
                subject.onNext(content);
                LogUtil.d("onEvent", "eventName: " + tag);
            }
        }
    }
}
