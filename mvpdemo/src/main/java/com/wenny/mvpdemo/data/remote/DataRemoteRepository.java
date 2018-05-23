package com.wenny.mvpdemo.data.remote;

import com.wenny.mvpdemo.model.ZhiHuHomeBean;

import io.reactivex.Observable;
import retrofit2.Retrofit;

/**
 * Created by Administrator on 2018/5/23.
 */

public class DataRemoteRepository implements DataRemoteSource {

    @Override
    public Observable<ZhiHuHomeBean> loadZhihuHome() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(UrlConstents.BASE_URL).build();
        RetrofitService retrofitService = retrofit.create(RetrofitService.class);
        Observable<ZhiHuHomeBean> zhihuHome = retrofitService.getZhihuHome();
        return zhihuHome;
    }
}
