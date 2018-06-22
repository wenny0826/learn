package com.wenny.mvpdemo.presenter;

import com.wenny.mvpdemo.base.BasePresenter;
import com.wenny.mvpdemo.data.DataRepository;
import com.wenny.mvpdemo.data.entity.NewInfoExtraBean;
import com.wenny.mvpdemo.data.entity.NewsInfoBean;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Administrator on 2018/6/20.
 */

public class NewsInfoPresenter extends BasePresenter<NewsInfoContact.View> implements NewsInfoContact.Presenter {
    DataRepository dataRepository;

    public NewsInfoPresenter(DataRepository dataRepository) {
        this.dataRepository = dataRepository;
    }

    @Override
    public void getData(String newsId) {
        dataRepository.getNewsInfo(newsId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<NewsInfoBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(NewsInfoBean newsInfoBean) {
                        if (newsInfoBean != null && isViewAttached()) {
                            getView().getData(newsInfoBean);
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

    @Override
    public void getExtra(String newsid) {
        dataRepository.getNewsInfoExtra(newsid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<NewInfoExtraBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(NewInfoExtraBean newInfoExtraBean) {
                        if (newInfoExtraBean != null && isViewAttached()) {
                            getView().getNewsExtra(newInfoExtraBean);
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
