package com.wenny.mvpdemo.presenter;

import com.wenny.mvpdemo.base.BaseView;
import com.wenny.mvpdemo.data.entity.NewInfoExtraBean;
import com.wenny.mvpdemo.data.entity.NewsInfoBean;

/**
 * Created by Administrator on 2018/6/20.
 */

public interface NewsInfoContact {
    interface View extends BaseView {
        void getData(NewsInfoBean newsInfoBean);
        void getNewsExtra(NewInfoExtraBean newInfoExtraBean);
    }

    interface Presenter {
        void getData(String newsId);
        void getExtra(String newsid);
    }
}
