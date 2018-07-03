package com.wenny.mvpdemo.base;

import android.app.Application;

import com.wenny.mvpdemo.data.DataRepository;
import com.wenny.mvpdemo.data.local.DaoMaster;
import com.wenny.mvpdemo.data.local.DaoSession;

/**
 * Created by Administrator on 2018/5/23.
 */

public class MyApplication extends Application {
    public static DataRepository dataRepository;
    public static boolean isNetWork = true;
    @Override
    public void onCreate() {
        super.onCreate();
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(getApplicationContext(), "wenny.db", null);
        DaoMaster daoMaster = new DaoMaster(devOpenHelper.getWritableDb());
        DaoSession daoSession = daoMaster.newSession();
        dataRepository = new DataRepository(daoSession);
    }

}
