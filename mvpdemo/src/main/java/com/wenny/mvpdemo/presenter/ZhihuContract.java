package com.wenny.mvpdemo.presenter;

import com.wenny.mvpdemo.BasePresenter;
import com.wenny.mvpdemo.BaseView;
import com.wenny.mvpdemo.model.ZhihuBanberBean;
import com.wenny.mvpdemo.model.ZhihuListBean;

import java.util.List;

/**
 * Created by Administrator on 2018/5/22.
 */

public interface ZhihuContract {
    interface View extends BaseView<Presenter> {
        void showBanber(List<ZhihuBanberBean> banberBeans);
        void showList(List<ZhihuListBean> zhihuListBeans);
    }
    interface Presenter extends BasePresenter {
        void loadData();
    }
}
