package com.wenny.mvpdemo.data.remote;

import com.wenny.mvpdemo.data.entity.NewInfoExtraBean;
import com.wenny.mvpdemo.data.entity.NewsInfoBean;
import com.wenny.mvpdemo.data.entity.ZhiHuHomeBean;
import com.wenny.mvpdemo.data.entity.ZhiHuListBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Administrator on 2018/5/23.
 */

public interface RetrofitService {
    @GET(UrlConstents.GET_NEW_DATA)
    Observable<ZhiHuHomeBean> getZhihuHome();

    @GET(UrlConstents.GET_NEW_NEXt)
    Observable<ZhiHuListBean> loadGhiHuNewNext(@Path("data") String data);

    @GET(UrlConstents.GET_NEWS_INFO)
    Observable<NewsInfoBean> getNewsInfo(@Path("newsid") String newsid);

    @GET(UrlConstents.GET_NEWS_OTHER_INFO)
    Observable<NewInfoExtraBean> getNewsInfoExtra(@Path("newsid") String newsid);
}
