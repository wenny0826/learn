package com.wenny.mvpdemo.presenter;

import android.util.Log;

import com.wenny.mvpdemo.data.DataRepository;
import com.wenny.mvpdemo.data.remote.RetrofitService;
import com.wenny.mvpdemo.data.remote.UrlConstents;
import com.wenny.mvpdemo.model.ZhiHuHomeBean;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2018/5/22.
 */

public class ZhihuPresenter implements ZhihuContract.Presenter {
    ZhihuContract.View mView;
    private DataRepository dataRepository;

    public ZhihuPresenter(DataRepository dataRepository, ZhihuContract.View mView) {
        this.dataRepository = dataRepository;
        this.mView = mView;
        mView.setPresenter(this);
    }

    @Override
    public void start() {
        loadData();
    }

    @Override
    public void loadData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(UrlConstents.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        RetrofitService retrofitService = retrofit.create(RetrofitService.class);
        Observable<ZhiHuHomeBean> zhihuHome = retrofitService.getZhihuHome();
        zhihuHome.subscribeOn(Schedulers.io())
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
                            mView.showList(zhiHuHomeBean.getStories());
                            mView.showBanber(zhiHuHomeBean.getTop_stories());
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
}
