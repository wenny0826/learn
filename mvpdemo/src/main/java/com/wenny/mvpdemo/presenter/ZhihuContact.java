package com.wenny.mvpdemo.presenter;

import com.wenny.mvpdemo.base.BaseView;
import com.wenny.mvpdemo.data.entity.ZhiHuHomeBean;
import com.wenny.mvpdemo.data.entity.ZhiHuListBean;

/**
 * Created by Administrator on 2018/5/24.
 */

public interface ZhihuContact {

    interface View extends BaseView {
        void getData(ZhiHuHomeBean zhiHuHomeBean,boolean isrefresh);
        void loadNext(ZhiHuListBean zhiHuListBean);
    }

    interface Presenter {
        void getData(boolean isRefresh);
        void loadNext();
    }

}
