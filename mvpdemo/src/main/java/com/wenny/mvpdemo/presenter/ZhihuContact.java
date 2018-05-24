package com.wenny.mvpdemo.presenter;

import com.wenny.mvpdemo.base.BaseView;
import com.wenny.mvpdemo.entity.ZhihuBanberBean;
import com.wenny.mvpdemo.entity.ZhihuListBean;

import java.util.List;

/**
 * Created by Administrator on 2018/5/24.
 */

public interface ZhihuContact {

    interface View extends BaseView {
        void showBanner(List<ZhihuBanberBean> zhihuBanberBeans);

        void showList(List<ZhihuListBean> zhihuListBeans0);
    }

    interface Presenter {
        void getData();
        void refresh();
        void loadNext();
    }

}
