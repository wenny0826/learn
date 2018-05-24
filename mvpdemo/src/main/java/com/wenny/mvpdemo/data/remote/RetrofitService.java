package com.wenny.mvpdemo.data.remote;

import com.wenny.mvpdemo.entity.ZhiHuHomeBean;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by Administrator on 2018/5/23.
 */

public interface RetrofitService {
    @GET(UrlConstents.LOAD_NEW_DATA)
    Observable<ZhiHuHomeBean> getZhihuHome();
}
