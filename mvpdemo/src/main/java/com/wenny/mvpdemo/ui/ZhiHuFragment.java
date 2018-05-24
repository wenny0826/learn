package com.wenny.mvpdemo.ui;

import android.support.v4.widget.NestedScrollView;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.wenny.mvpdemo.R;
import com.wenny.mvpdemo.entity.ZhihuBanberBean;
import com.wenny.mvpdemo.entity.ZhihuListBean;
import com.wenny.mvpdemo.presenter.ZhihuContact;
import com.wenny.mvpdemo.presenter.ZhihuPresenter;
import com.wenny.mvpdemo.ui.adapter.ZhihuBannerAdapter;
import com.wenny.mvpdemo.base.BaseFragment;
import com.wenny.mvpdemo.weigth.AutoScrollViewPager.AutoScrollViewPager;

import java.util.List;

import me.relex.circleindicator.CircleIndicator;

/**
 * Created by Administrator on 2018/5/21.
 */

public class ZhiHuFragment extends BaseFragment implements ZhihuContact.View {

    private String TAG = "ZhiHuFragment";

    private SwipeRefreshLayout swipeRefresh;
    private NestedScrollView nestedScrollview;
    private AutoScrollViewPager viewpager;
    private CircleIndicator circleIndicator;
    private RecyclerView recyclerview;
    private ZhihuBannerAdapter zhihuBannerAdapter;

    private ZhihuPresenter presenter;

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

        zhihuBannerAdapter = new ZhihuBannerAdapter(getContext());
        viewpager.setAdapter(zhihuBannerAdapter);

        presenter = new ZhihuPresenter();
        presenter.attachView(this);
        presenter.getData();
    }

    @Override
    public void onResume() {
        super.onResume();
    }


    @Override
    public void showBanner(List<ZhihuBanberBean> zhihuBanberBeans) {
        Log.d(TAG, "showBanber: ");
        zhihuBannerAdapter.setZhihuBanberBeans(zhihuBanberBeans);
        circleIndicator.setViewPager(viewpager);
        viewpager.startAutoScroll();
    }

    @Override
    public void showList(List<ZhihuListBean> zhihuListBeans) {
        Log.d(TAG, "showList: ");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }
}
