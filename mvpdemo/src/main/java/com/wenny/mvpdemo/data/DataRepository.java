package com.wenny.mvpdemo.data;

import com.wenny.mvpdemo.data.remote.RetrofitService;
import com.wenny.mvpdemo.data.remote.UrlConstents;
import com.wenny.mvpdemo.data.entity.ZhiHuHomeBean;
import com.wenny.mvpdemo.data.entity.ZhiHuListBean;

import io.reactivex.Observable;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2018/5/23.
 */

public class DataRepository{

    private  RetrofitService retrofitService;

    public DataRepository() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(UrlConstents.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        retrofitService = retrofit.create(RetrofitService.class);
    }

    public  Observable<ZhiHuHomeBean> getZhihuHome() {
        Observable<ZhiHuHomeBean> zhihuHome = retrofitService.getZhihuHome();
        return zhihuHome;
    }
    public  Observable<ZhiHuListBean> loadNewNext(String data) {
        return retrofitService.loadGhiHuNewNext(data);
    }
}
