package com.wenny.mvpdemo.data;

import com.wenny.mvpdemo.data.remote.DataRemoteRepository;
import com.wenny.mvpdemo.model.ZhiHuHomeBean;

/**
 * Created by Administrator on 2018/5/23.
 */

public class DataRepository implements DataSource{

    private DataRemoteRepository dataRemoteRepository;
    @Override
    public ZhiHuHomeBean getZhihuHome() {

        return null;
    }

    private void loadZhihuList(){

    }
}
