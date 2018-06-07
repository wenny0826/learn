package com.wenny.mvpdemo.data.remote;

import com.wenny.mvpdemo.data.entity.ZhiHuHomeBean;
import com.wenny.mvpdemo.data.entity.ZhiHuListBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Administrator on 2018/5/23.
 */

public interface RetrofitService {
    @GET(UrlConstents.LOAD_NEW_DATA)
    Observable<ZhiHuHomeBean> getZhihuHome();

    @GET(UrlConstents.LOAD_NEW_NEXt)
    Observable<ZhiHuListBean> loadGhiHuNewNext(@Path("data") String data);
}
