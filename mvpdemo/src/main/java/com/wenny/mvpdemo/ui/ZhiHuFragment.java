package com.wenny.mvpdemo.ui;

import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.wenny.mvpdemo.R;
import com.wenny.mvpdemo.base.BaseFragment;
import com.wenny.mvpdemo.base.MyApplication;
import com.wenny.mvpdemo.data.entity.ZhiHuHomeBean;
import com.wenny.mvpdemo.data.entity.ZhiHuListBean;
import com.wenny.mvpdemo.evenbus.ChangeTitleEven;
import com.wenny.mvpdemo.presenter.ZhihuContact;
import com.wenny.mvpdemo.presenter.ZhihuPresenter;
import com.wenny.mvpdemo.ui.adapter.ZhihuBannerAdapter;
import com.wenny.mvpdemo.ui.adapter.ZhihuListAdapter;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.Map;

import me.relex.circleindicator.CircleIndicator;

/**
 * Created by Administrator on 2018/5/21.
 */

public class ZhiHuFragment extends BaseFragment implements ZhihuContact.View {

    private String TAG = "ZhiHuFragment";

    private SwipeRefreshLayout swipeRefresh;
    private NestedScrollView nestedScrollview;
    private ViewPager viewpager;
    private CircleIndicator circleIndicator;
    private RecyclerView recyclerview;
    private ZhihuBannerAdapter zhihuBannerAdapter;
    private ZhihuListAdapter zhihuListAdapter;
    private ZhihuPresenter presenter;
    private LinearLayoutManager linearLayoutManager;

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
        zhihuListAdapter = new ZhihuListAdapter(getContext());
        linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setRecycleChildrenOnDetach(true);

        recyclerview.setLayoutManager(linearLayoutManager);
        recyclerview.setAdapter(zhihuListAdapter);
        recyclerview.setNestedScrollingEnabled(false);
        presenter = new ZhihuPresenter(MyApplication.dataRepository);
        presenter.attachView(this);
        presenter.getData(false);
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.getData(true);
            }
        });
        nestedScrollview.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if (scrollY > oldScrollY) {
//                    Log.i(TAG, "Scroll DOWN");
                    swipeRefresh.setRefreshing(false);
                    scrollDown(scrollY);
                }
                if (scrollY < oldScrollY) {
//                    Log.i(TAG, "Scroll UP");
                    swipeRefresh.setRefreshing(false);
                    scrollUp(scrollY);
                }

                if (scrollY == 0) {
                }

                if (scrollY== (v.getChildAt(0).getMeasuredHeight() - v.getMeasuredHeight())) {
                    presenter.loadNext();
                }
            }
        });
    }
    int nowPosition = -1;
    private void scrollUp(int scrollY){
        if (nowPosition > -1){
            if ((scrollY - viewpager.getHeight()) < positionHeight.get(nowPosition)){
                nowPosition --;
                changeTitie(nowPosition);
            }
        }
    }
    private void scrollDown(int scrollY){
        if (positionHeight.size()>(nowPosition+1)&&(scrollY - viewpager.getHeight()) > positionHeight.get(nowPosition+1)){
            nowPosition++;
            changeTitie(nowPosition);
        }
    }
    private void changeTitie(int pos){
        if (pos == -1){
            EventBus.getDefault().post(new ChangeTitleEven(getResources().getString(R.string.app_zhihu_home)));
        }else {
            EventBus.getDefault().post(new ChangeTitleEven(zhihuListAdapter.getTitle(pos)));
        }
    }

    @Override
    public void onResume() {
        super.onResume();
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }

    private Map<Integer, Integer> positionHeight = new HashMap<>();

    @Override
    public void getData(ZhiHuHomeBean zhiHuHomeBean, boolean isrefresh) {
        zhihuBannerAdapter.setZhihuBanberBeans(zhiHuHomeBean.getTop_stories());
        circleIndicator.setViewPager(viewpager);
//        viewpager.startAutoScroll();
        ZhiHuListBean zhiHuListBean = new ZhiHuListBean();
        zhiHuListBean.setDate(getResources().getString(R.string.app_zhihu_today));
        zhiHuListBean.setStories(zhiHuHomeBean.getStories());
        zhihuListAdapter.setData(zhiHuListBean);
        Log.d(TAG, "getData: " + recyclerview.getHeight());
        positionHeight.clear();
        positionHeight.put(0, 0);
        if (isrefresh) {
            swipeRefresh.setRefreshing(false);
        }
    }

    @Override
    public void loadNext(ZhiHuListBean zhiHuListBean) {
        zhihuListAdapter.addData(zhiHuListBean);
        Log.d(TAG, "loadNext: " + recyclerview.getHeight());
        positionHeight.put(zhihuListAdapter.getItemCount() - 1,recyclerview.getHeight());
    }
}
