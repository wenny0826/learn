package com.wenny.mvpdemo.presenter;

import android.util.Log;

import com.wenny.mvpdemo.base.BasePresenter;
import com.wenny.mvpdemo.base.MyApplication;
import com.wenny.mvpdemo.data.DataRepository;
import com.wenny.mvpdemo.data.entity.ZhiHuHomeBean;
import com.wenny.mvpdemo.data.entity.ZhiHuListBean;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Administrator on 2018/5/22.
 */

public class ZhihuPresenter extends BasePresenter<ZhihuContact.View> implements ZhihuContact.Presenter {

    private String pageTime;

    DataRepository dataRepository;

    public ZhihuPresenter(DataRepository dataRepository) {
        this.dataRepository = dataRepository;
    }

    @Override
    public void getData(boolean isRefresh) {
        if (!MyApplication.isNetWork){
            ZhiHuHomeBean dbZhiHuHome = dataRepository.getDBZhiHuHome();
            if (dbZhiHuHome != null){
                getView().getData(dbZhiHuHome,isRefresh);
                pageTime = dbZhiHuHome.getDate();
            }
        }else {
            dataRepository.getZhihuHome()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<ZhiHuHomeBean>() {
                        @Override
                        public void onSubscribe(Disposable d) {
                            Log.d("print", "onSubscribe: ");

                        }

                        @Override
                        public void onNext(ZhiHuHomeBean zhiHuHomeBean) {
                            if (zhiHuHomeBean != null && isViewAttached()) {
                                getView().getData(zhiHuHomeBean, isRefresh);
                                dataRepository.updataNewsHome(zhiHuHomeBean);
                                pageTime = zhiHuHomeBean.getDate();
                            }
                        }

                        @Override
                        public void onError(Throwable e) {
                            e.printStackTrace();
                        }

                        @Override
                        public void onComplete() {

                        }
                    });
        }
    }


    @Override
    public void loadNext() {
        Log.d("print", "loadNext: " +pageTime);
        if (!MyApplication.isNetWork && pageTime != null){
            ZhiHuListBean dbNewNext = dataRepository.getDBNewNext(pageTime);
            if (dbNewNext != null){
                Log.d("print", "loadNext: db ");
                getView().loadNext(dbNewNext);
                pageTime = dbNewNext.getDate();
            }
        }else if (pageTime != null){
            dataRepository.loadNewNext(pageTime)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<ZhiHuListBean>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(ZhiHuListBean zhiHuListBean) {
                            if (zhiHuListBean != null && isViewAttached()) {
                                getView().loadNext(zhiHuListBean);
                                dataRepository.updataNewsPage(zhiHuListBean,pageTime);
                                pageTime = zhiHuListBean.getDate();
                                Log.d("print", "onNext: net"+zhiHuListBean.getDate()+" "+pageTime);

                            }
                        }

                        @Override
                        public void onError(Throwable e) {

                        }

                        @Override
                        public void onComplete() {

                        }
                    });
        }
    }
}
