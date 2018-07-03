package com.wenny.mvpdemo.evenbus;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;

import com.wenny.mvpdemo.base.BaseActivity;
import com.wenny.mvpdemo.util.NetUtil;

/**
 * Created by Administrator on 2018/6/27.
 */

public class NetWorkStateReceiver extends BroadcastReceiver {
    public NetEvevt evevt = BaseActivity.evevt;

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO Auto-generated method stub
        // 如果相等的话就说明网络状态发生了变化
        if (intent.getAction().equals(ConnectivityManager.CONNECTIVITY_ACTION)) {
            int netWorkState = NetUtil.getNetWorkState(context);
            // 接口回调传过去状态的类型
            evevt.onNetChange(netWorkState);
        }
    }

    // 自定义接口
    public interface NetEvevt {
        void onNetChange(int netMobile);
    }

}
