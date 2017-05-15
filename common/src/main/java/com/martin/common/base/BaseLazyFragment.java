package com.martin.common.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * 懒加载基类
 */

public abstract class BaseLazyFragment<T extends BasePresenter, E extends BaseModel> extends BaseFragment<T, E> {


    private boolean mInited = false;
    private Bundle mSavedInstanceState;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSavedInstanceState = savedInstanceState;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState == null) {
            if (!isHidden()) {
                mInited = true;
                initLazyView(null);
            }
        } else {
            // TODO: 2017/5/3
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        // 未初始化且可见状态
        if (!mInited && !hidden) {
            mInited = true;
            initLazyView(mSavedInstanceState);
        }
    }

    protected abstract void initLazyView(@Nullable Bundle savedInstanceState);
}
