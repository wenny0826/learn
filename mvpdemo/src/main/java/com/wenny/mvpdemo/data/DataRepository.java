package com.wenny.mvpdemo.data;

import com.wenny.mvpdemo.data.entity.NewInfoExtraBean;
import com.wenny.mvpdemo.data.entity.NewsInfoBean;
import com.wenny.mvpdemo.data.entity.ZhiHuHomeBean;
import com.wenny.mvpdemo.data.entity.ZhiHuListBean;
import com.wenny.mvpdemo.data.entity.ZhihuBanberBean;
import com.wenny.mvpdemo.data.entity.ZhihuNewBean;
import com.wenny.mvpdemo.data.local.DaoSession;
import com.wenny.mvpdemo.data.local.ZhiHuHomeBeanDao;
import com.wenny.mvpdemo.data.local.ZhiHuListBeanDao;
import com.wenny.mvpdemo.data.local.ZhihuBanberBeanDao;
import com.wenny.mvpdemo.data.local.ZhihuNewBeanDao;
import com.wenny.mvpdemo.data.remote.RetrofitService;
import com.wenny.mvpdemo.data.remote.UrlConstents;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2018/5/23.
 */

public class DataRepository {

    private DaoSession daoSession;

    private RetrofitService retrofitService;

    public DataRepository(DaoSession daoSession) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(UrlConstents.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        retrofitService = retrofit.create(RetrofitService.class);
        this.daoSession = daoSession;
    }

    public Observable<ZhiHuHomeBean> getZhihuHome() {
        Observable<ZhiHuHomeBean> zhihuHome = retrofitService.getZhihuHome();
        return zhihuHome;
    }

    public Observable<ZhiHuListBean> loadNewNext(String data) {
        return retrofitService.loadGhiHuNewNext(data);
    }

    public Observable<NewsInfoBean> getNewsInfo(String newsId) {
        return retrofitService.getNewsInfo(newsId);
    }

    public Observable<NewInfoExtraBean> getNewsInfoExtra(String newsId) {
        return retrofitService.getNewsInfoExtra(newsId);
    }

    public void updataNewsPage(ZhiHuListBean zhiHuListBean,String pagedate) {
        zhiHuListBean.setPagedate(pagedate);
        ZhiHuListBeanDao zhiHuListBeanDao = daoSession.getZhiHuListBeanDao();
        ZhiHuListBean unique = zhiHuListBeanDao.queryBuilder().where(ZhiHuListBeanDao.Properties.Date.eq(zhiHuListBean.getDate())).unique();
        if (unique != null) {
            zhiHuListBeanDao.delete(zhiHuListBean);
            zhiHuListBeanDao.insert(zhiHuListBean);
            insertNews(zhiHuListBean.getStories(),zhiHuListBean.getDate());
        } else {
            zhiHuListBeanDao.insert(zhiHuListBean);
            insertNews(zhiHuListBean.getStories(),zhiHuListBean.getDate());
        }
    }

    public void updataNewsHome(ZhiHuHomeBean zhiHuHomeBean) {
        ZhiHuHomeBeanDao zhiHuHomeBeanDao = daoSession.getZhiHuHomeBeanDao();
        ZhiHuHomeBean unique = zhiHuHomeBeanDao.queryBuilder().unique();
        if (unique != null) {
            //更新
            zhiHuHomeBeanDao.delete(unique);
            zhiHuHomeBeanDao.insert(zhiHuHomeBean);

            insertBanner(zhiHuHomeBean.getTop_stories(),zhiHuHomeBean.getDate());
            insertNews(zhiHuHomeBean.getStories(),zhiHuHomeBean.getDate());
        } else {
            //插入
            zhiHuHomeBeanDao.insert(zhiHuHomeBean);
            insertBanner(zhiHuHomeBean.getTop_stories(),zhiHuHomeBean.getDate());
            insertNews(zhiHuHomeBean.getStories(),zhiHuHomeBean.getDate());
        }
    }

    public ZhiHuHomeBean getDBZhiHuHome() {
        ZhiHuHomeBean zhiHuHomeBean = null;
        zhiHuHomeBean = daoSession.getZhiHuHomeBeanDao().queryBuilder().unique();
        return zhiHuHomeBean;
    }

    public ZhiHuListBean getDBNewNext(String data) {
        ZhiHuListBean zhiHuListBean = null;
        ZhiHuListBean unique = daoSession.getZhiHuListBeanDao().queryBuilder().where(ZhiHuListBeanDao.Properties.Pagedate.eq(data)).unique();
        if (unique != null) {
            zhiHuListBean = unique;
        }

        return zhiHuListBean;
    }

    private void insertNews(List<ZhihuNewBean> zhihuNewBeans,String date) {
        ZhihuNewBeanDao zhihuNewBeanDao = daoSession.getZhihuNewBeanDao();
        for (ZhihuNewBean zhihuNewBean : zhihuNewBeans) {
            zhihuNewBean.setDate(date);
            if (zhihuNewBeanDao.queryBuilder().where(ZhihuNewBeanDao.Properties.Id.eq(zhihuNewBean.getId())).build().unique() != null) {
                zhihuNewBeanDao.update(zhihuNewBean);
            } else {
                zhihuNewBeanDao.insert(zhihuNewBean);
            }
        }

    }

    private void insertBanner(List<ZhihuBanberBean> zhihuBanberBeans,String date) {
        ZhihuBanberBeanDao zhihuBanberBeanDao = daoSession.getZhihuBanberBeanDao();
        for (ZhihuBanberBean zhihuBanberBean : zhihuBanberBeans) {
            zhihuBanberBean.setDate(date);
            if (zhihuBanberBeanDao.queryBuilder().where(ZhihuBanberBeanDao.Properties.Id.eq(zhihuBanberBean.getId())).build().unique() != null) {
                zhihuBanberBeanDao.update(zhihuBanberBean);
            } else {
                zhihuBanberBeanDao.insert(zhihuBanberBean);
            }
        }
    }
}
