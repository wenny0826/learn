package com.wenny.mvpdemo.base;

import android.app.Application;

import com.wenny.mvpdemo.data.DataRepository;

/**
 * Created by Administrator on 2018/5/23.
 */

public class MyApplication extends Application {
    public static DataRepository dataRepository;
    @Override
    public void onCreate() {
        super.onCreate();
        dataRepository = new DataRepository();
    }
}
