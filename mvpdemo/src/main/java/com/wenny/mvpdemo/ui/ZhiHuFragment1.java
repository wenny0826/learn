package com.wenny.mvpdemo.ui;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.wenny.mvpdemo.R;
import com.wenny.mvpdemo.base.BaseFragment;
import com.wenny.mvpdemo.base.MyApplication;
import com.wenny.mvpdemo.data.entity.ZhiHuHomeBean;
import com.wenny.mvpdemo.data.entity.ZhiHuListBean;
import com.wenny.mvpdemo.evenbus.ChangeTitleEven;
import com.wenny.mvpdemo.presenter.ZhihuContact;
import com.wenny.mvpdemo.presenter.ZhihuPresenter;
import com.wenny.mvpdemo.ui.adapter.ZhihuHomeAdapter;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/6/7.
 */

public class ZhiHuFragment1 extends BaseFragment implements ZhihuContact.View, ZhihuHomeAdapter.newsItemClickListener {
    private RecyclerView recyclerview;
    private String TAG = "ZhiHuFragment";
    private SwipeRefreshLayout swipeRefresh;
    private ZhihuHomeAdapter zhihuHomeAdapter;
    private ZhihuPresenter presenter;
    private LinearLayoutManager linearLayoutManager;
    private int firstVisibleItemPosition;

    @Override
    protected int getContentId() {
        return R.layout.fragment_zhihu_home;
    }

    @Override
    protected void init(View view) {
        super.init(view);
        recyclerview = view.findViewById(R.id.recyclerview);
        swipeRefresh = view.findViewById(R.id.swipeRefresh);
        zhihuHomeAdapter = new ZhihuHomeAdapter(getContext());
        linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerview.setLayoutManager(linearLayoutManager);
        recyclerview.setAdapter(zhihuHomeAdapter);
        presenter = new ZhihuPresenter(MyApplication.dataRepository);
        presenter.attachView(this);
        presenter.getData(false);
        zhihuHomeAdapter.setNewsItemClickListener(this);
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.getData(true);
            }
        });
        recyclerview.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                firstVisibleItemPosition = linearLayoutManager.findFirstVisibleItemPosition();
                if (isSlideToBottom(recyclerView)) {
                    presenter.loadNext();
                }
                if (dy > 0) {
                    //下滑
                    scrollDown();
                } else {
                    //上滑
                    scrollUp();
                }
            }
        });
    }

    int nowPosition = -1;

    private void scrollUp() {
        if (nowPosition > -1 && integers.size() > nowPosition) {
            if (firstVisibleItemPosition < integers.get(nowPosition).getPosition()) {
                nowPosition--;
                changeTitie(integers.get(nowPosition).getTitle());
            }
        }
    }

    private void scrollDown() {
        if (integers.size() > nowPosition + 1) {
            if (integers.get(nowPosition + 1).getPosition() <= firstVisibleItemPosition) {
                nowPosition++;
                changeTitie(integers.get(nowPosition).getTitle());
            }
        }

    }

    private void changeTitie(String title) {
        EventBus.getDefault().post(new ChangeTitleEven(title));
    }

    public static boolean isSlideToBottom(RecyclerView recyclerView) {
        if (recyclerView == null) return false;
        if (recyclerView.computeVerticalScrollExtent() + recyclerView.computeVerticalScrollOffset()
                >= recyclerView.computeVerticalScrollRange())
            return true;
        return false;
    }

    List<DateBean> integers = new ArrayList<>();

    @Override
    public void getData(ZhiHuHomeBean zhiHuHomeBean, boolean isrefresh) {
        zhihuHomeAdapter.setData(zhiHuHomeBean);
        if (isrefresh) {
            swipeRefresh.setRefreshing(false);
        }
        integers.clear();
        integers.add(new DateBean(0, getResources().getString(R.string.app_zhihu_home)));
        integers.add(new DateBean(1, getResources().getString(R.string.app_zhihu_today)));
    }

    @Override
    public void loadNext(ZhiHuListBean zhiHuListBean) {
        integers.add(new DateBean(zhihuHomeAdapter.getItemCount(), zhiHuListBean.getShowData()));
        zhihuHomeAdapter.addData(zhiHuListBean);
    }

    @Override
    public void onNewsItemClick(String newsid) {
        Intent intent = new Intent(getActivity(), NewsInfoActivity.class);
        intent.putExtra("newsid", newsid);
        startActivity(intent);
    }

    public class DateBean {
        int position;
        String title;

        public DateBean(int position, String title) {
            this.position = position;
            this.title = title;
        }

        public int getPosition() {
            return position;
        }

        public void setPosition(int position) {
            this.position = position;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
