package com.martin.mvplearning;

import android.Manifest;
import android.os.Bundle;
import android.os.Handler;

import com.martin.common.base.BaseActivity;
import com.martin.common.base.utils.RxUtil;
import com.tbruyelle.rxpermissions.Permission;
import com.tbruyelle.rxpermissions.RxPermissions;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.functions.Action1;

/**
 * 开屏页
 */

public class SplashActivity extends BaseActivity {

    @Override
    protected void initView(Bundle savedInstanceState) {
//        Action1<Permission> onNextAction = new Action1<Permission>() {
//            @Override
//            public void call(Permission permission) {
//                if (permission.granted) {
//                        startActivity(MvpMainActivity.class);
//                        finish();
//                }
//            }
//        };
//        Observable.timer(2000, TimeUnit.MILLISECONDS)
//                .compose(RxPermissions.getInstance(this).ensureEach(Manifest.permission.READ_PHONE_STATE, Manifest.permission.CAMERA,
//                        Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.ACCESS_COARSE_LOCATION))
//                .compose(RxUtil.rxSchedulerHelper())
//                .subscribe(onNextAction);




        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(MvpMainActivity.class);
                finish();
            }
        },2000);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    public boolean isOpen() {
        return true;
    }

}
