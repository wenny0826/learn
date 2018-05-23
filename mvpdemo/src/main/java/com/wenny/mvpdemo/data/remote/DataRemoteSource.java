package com.wenny.mvpdemo.data.remote;

import com.wenny.mvpdemo.model.ZhiHuHomeBean;

import io.reactivex.Observable;

/**
 * Created by Administrator on 2018/5/23.
 */

public interface DataRemoteSource {
    Observable<ZhiHuHomeBean> loadZhihuHome();
}
