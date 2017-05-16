package com.martin.mvplearning.api;

import com.martin.mvplearning.main.DailyListBean;
import com.martin.mvplearning.main.ZhihuDetailBean;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by martin on 2017/5/16.
 */

public interface ZhiHuApi {


    @GET("news/latest")
    Observable<DailyListBean> getDailyList();

    @GET("news/{id}")
    Observable<ZhihuDetailBean> getDetailInfo(@Path("id") int id);
}
