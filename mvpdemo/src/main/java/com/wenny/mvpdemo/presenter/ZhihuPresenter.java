package com.wenny.mvpdemo.presenter;

import android.util.Log;

import com.wenny.mvpdemo.base.BasePresenter;
import com.wenny.mvpdemo.data.DataRepository;
import com.wenny.mvpdemo.entity.ZhiHuHomeBean;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Administrator on 2018/5/22.
 */

public class ZhihuPresenter extends BasePresenter<ZhihuContact.View> implements ZhihuContact.Presenter {

    @Override
    public void getData() {
        DataRepository.getZhihuHome()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ZhiHuHomeBean>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d("print", "onSubscribe: ");

            }

            @Override
            public void onNext(ZhiHuHomeBean zhiHuHomeBean) {
                Log.d("print", "onNext: ");
                if (zhiHuHomeBean != null) {
                    getView().showList(zhiHuHomeBean.getStories());
                    getView().showBanner(zhiHuHomeBean.getTop_stories());
                }
            }

            @Override
            public void onError(Throwable e) {
                Log.d("print", "onError: " + e.getMessage());
                e.printStackTrace();
            }

            @Override
            public void onComplete() {
                Log.d("print", "onComplete: ");

            }
        });
    }

    @Override
    public void refresh() {

    }

    @Override
    public void loadNext() {

    }
}
