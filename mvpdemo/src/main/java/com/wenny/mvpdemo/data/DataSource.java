package com.wenny.mvpdemo.data;

import com.wenny.mvpdemo.entity.ZhiHuHomeBean;

import io.reactivex.Observable;


/**
 * Created by Administrator on 2018/5/23.
 */

public interface DataSource {
    Observable<ZhiHuHomeBean> getZhihuHome();
}
