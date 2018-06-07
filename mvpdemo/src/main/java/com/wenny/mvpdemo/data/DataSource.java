package com.wenny.mvpdemo.data;

import com.wenny.mvpdemo.data.entity.ZhiHuHomeBean;

import io.reactivex.Observable;


/**
 * Created by Administrator on 2018/5/23.
 */

public interface DataSource {
    public Observable<ZhiHuHomeBean> getZhihuHome();
}
