package com.wenny.mvpdemo.ui;

import android.support.v4.widget.NestedScrollView;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.wenny.mvpdemo.R;
import com.wenny.mvpdemo.data.DataRepository;
import com.wenny.mvpdemo.model.ZhihuBanberBean;
import com.wenny.mvpdemo.model.ZhihuListBean;
import com.wenny.mvpdemo.presenter.ZhihuContract;
import com.wenny.mvpdemo.presenter.ZhihuPresenter;
import com.wenny.mvpdemo.ui.base.BaseFragment;
import com.wenny.mvpdemo.weigth.AutoScrollViewPager.AutoScrollViewPager;

import java.util.List;

import me.relex.circleindicator.CircleIndicator;

/**
 * Created by Administrator on 2018/5/21.
 */

public class ZhiHuFragment extends BaseFragment implements ZhihuContract.View{
    
    private String TAG = "ZhiHuFragment";
    
    private SwipeRefreshLayout swipeRefresh;
    private NestedScrollView nestedScrollview;
    private AutoScrollViewPager viewpager;
    private CircleIndicator circleIndicator;
    private RecyclerView recyclerview;

    private ZhihuContract.Presenter presenter;
    private DataRepository dataRepository;

    @Override
    protected int getContentId() {
        return R.layout.fragment_one;
    }

    @Override
    protected void init(View view) {
        super.init(view);
        swipeRefresh = view.findViewById(R.id.swipeRefresh);
        nestedScrollview = view.findViewById(R.id.nestedScrollview);
        viewpager = view.findViewById(R.id.viewpager);
        circleIndicator = view.findViewById(R.id.circleIndicator);
        recyclerview = view.findViewById(R.id.recyclerview);

        dataRepository = new DataRepository();
        presenter = new ZhihuPresenter(dataRepository,this);
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.start();
    }

    @Override
    public void showBanber(List<ZhihuBanberBean> banberBeans) {
        Log.d(TAG, "showBanber: ");
    }

    @Override
    public void showList(List<ZhihuListBean> zhihuListBeans) {
        Log.d(TAG, "showList: ");
    }

    @Override
    public void setPresenter(ZhihuContract.Presenter presenter) {
        this.presenter = presenter;
    }
}
