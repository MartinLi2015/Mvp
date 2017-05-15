package com.martin.common.base;

/**
 * 基类Presenter
 *
 * @author martin
 */

public abstract class BasePresenter<M, T> {

    public M mModel;
    public T mView;
    public RxManager mRxManager = new RxManager();


    public void setVM(T v, M m) {
        this.mView = v;
        this.mModel = m;
        this.onStart();
    }

    public abstract void onStart();

    public void onDestroy() {
        mRxManager.clear();
        mModel = null;
        mView = null;
    }

}
