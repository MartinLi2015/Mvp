package com.martin.common.base;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.martin.common.base.utils.LogUtil;
import com.martin.common.base.utils.TUtil;
import com.martin.common.base.utils.ToastUtil;
import com.martin.common.view.SwipeBackLayout;

/**
 * 基类
 *
 * @author martin
 */

public abstract class BaseFragment<T extends BasePresenter, E extends BaseModel> extends Fragment {

    public boolean isNight;
    public T mPresenter;
    public E mModel;
    public Context mContext;
    protected Activity mActivity;
    protected String TAG;
    private SwipeBackLayout swipeBackLayout;
    private ImageView ivShadow;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (Activity) context;
        mContext = context;

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (getLayoutView() != null) {
            return getLayoutView();
        } else {
            return inflater.inflate(getLayoutId(), container, false);
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mActivity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        TAG = getClass().getSimpleName();
        mPresenter = TUtil.getT(this, 0);
        mModel = TUtil.getT(this, 1);
        initView(view, savedInstanceState);
        if (this instanceof BaseView) mPresenter.setVM(this, mModel);
        getBundle(getArguments());
        initData();


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) mPresenter.onDestroy();
    }

    protected void setToolBar(Toolbar toolbar, String title) {
        toolbar.setTitle(title);
//        toolbar.setNavigationIcon(R.mipmap.ic_arrow_back_white_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: 2017/5/2
            }
        });
    }

    protected abstract void initView(View view, @Nullable Bundle savedInstanceState);

    protected abstract int getLayoutId();

    public View getLayoutView() {
        return null;
    }

    protected void getBundle(Bundle arguments) {

    }

    protected void initData() {

    }

    public void showToast(String msg) {
        ToastUtil.showToast(mContext, msg);
    }

    public void showToast(@StringRes int resId) {
        ToastUtil.showToast(mContext, resId);
    }

    public void showLog(String msg) {
        LogUtil.i(TAG, msg);
    }
}
