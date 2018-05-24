package com.wenny.mvpdemo.data;

import com.wenny.mvpdemo.data.remote.RetrofitService;
import com.wenny.mvpdemo.data.remote.UrlConstents;
import com.wenny.mvpdemo.entity.ZhiHuHomeBean;

import io.reactivex.Observable;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2018/5/23.
 */

public class DataRepository {

    public static Observable<ZhiHuHomeBean> getZhihuHome() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(UrlConstents.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        RetrofitService retrofitService = retrofit.create(RetrofitService.class);
        Observable<ZhiHuHomeBean> zhihuHome = retrofitService.getZhihuHome();
        return zhihuHome;
    }
}
