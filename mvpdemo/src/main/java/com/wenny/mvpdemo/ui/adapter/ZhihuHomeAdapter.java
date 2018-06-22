package com.wenny.mvpdemo.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wenny.mvpdemo.R;
import com.wenny.mvpdemo.data.entity.ZhiHuHomeBean;
import com.wenny.mvpdemo.data.entity.ZhiHuListBean;
import com.wenny.mvpdemo.data.entity.ZhihuBanberBean;
import com.wenny.mvpdemo.data.entity.ZhihuNewBean;
import com.wenny.mvpdemo.weigth.AutoScrollViewPager.AutoScrollViewPager;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator;

/**
 * Created by Administrator on 2018/6/7.
 */

public class ZhihuHomeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static final int HEAD = 1;
    private List<ZhihuBanberBean> zhihuBanberBeans;
    private List<Object> list;
    private Context context;
    private ZhihuBannerAdapter zhihuBannerAdapter;
    private newsItemClickListener newsItemClickListener;

    public void setNewsItemClickListener(ZhihuHomeAdapter.newsItemClickListener newsItemClickListener) {
        this.newsItemClickListener = newsItemClickListener;
    }

    public ZhihuHomeAdapter(Context context) {
        this.context = context;
        zhihuBanberBeans = new ArrayList<>();
        list = new ArrayList<>();
    }

    public void setData(ZhiHuHomeBean zhiHuHomeBean) {
        list.clear();
        zhihuBanberBeans = zhiHuHomeBean.getTop_stories();
        list.add(context.getResources().getString(R.string.app_zhihu_today));
        list.addAll(zhiHuHomeBean.getStories());
        this.notifyDataSetChanged();
    }

    public void addData(ZhiHuListBean zhiHuListBean) {
        list.add(zhiHuListBean.getShowData());
        list.addAll(zhiHuListBean.getStories());
        this.notifyItemRangeInserted(getItemCount() - 1, zhiHuListBean.getStories().size() + 1);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == 0) {
            return new HeadViewHolder(LayoutInflater.from(context).inflate(R.layout.layout_zhihu_home_head, parent, false));
        } else if (viewType == 1) {
            return new DateViewHolder(LayoutInflater.from(context).inflate(R.layout.item_zhihu_home_date, parent, false));
        }
        return new NewsViewHolder(LayoutInflater.from(context).inflate(R.layout.item_zhihu_list, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == 0) {
            HeadViewHolder headViewHolder = (HeadViewHolder) holder;
            zhihuBannerAdapter = new ZhihuBannerAdapter(context);
            headViewHolder.viewpager.setAdapter(zhihuBannerAdapter);
            zhihuBannerAdapter.setZhihuBanberBeans(zhihuBanberBeans);
            headViewHolder.circleIndicator.setViewPager(headViewHolder.viewpager);
            headViewHolder.viewpager.startAutoScroll();
        } else if (getItemViewType(position) == 1) {
            String date = (String) list.get(position-HEAD);
            DateViewHolder dateViewHolder = (DateViewHolder) holder;
            dateViewHolder.tv_data.setText(date);
        } else {
            NewsViewHolder newsViewHolder = (NewsViewHolder) holder;
            ZhihuNewBean zhihuNewBean = (ZhihuNewBean) list.get(position - HEAD);
            newsViewHolder.tv_title.setText(zhihuNewBean.getTitle());
            if (zhihuNewBean.getImages() != null){
                Glide.with(context).asBitmap().load(zhihuNewBean.getImages().get(0)).into(newsViewHolder.cover);
            }
        }
    }

    @Override
    public int getItemCount() {
        return list.size() + HEAD;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return 0;
        } else if (list.get(position - HEAD) instanceof String) {
            return 1;
        }
        return 2;
    }

    public class HeadViewHolder extends RecyclerView.ViewHolder {
        AutoScrollViewPager viewpager;
        CircleIndicator circleIndicator;

        public HeadViewHolder(View itemView) {
            super(itemView);
            viewpager = itemView.findViewById(R.id.viewpager);
            circleIndicator = itemView.findViewById(R.id.circleIndicator);
        }
    }

    public class DateViewHolder extends RecyclerView.ViewHolder {
        TextView tv_data;

        public DateViewHolder(View itemView) {
            super(itemView);
            tv_data = itemView.findViewById(R.id.tv_data);
        }
    }

    public class NewsViewHolder extends RecyclerView.ViewHolder {
        TextView tv_title;
        ImageView cover;

        public NewsViewHolder(View itemView) {
            super(itemView);
            tv_title = itemView.findViewById(R.id.tv_title);
            cover = itemView.findViewById(R.id.cover);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (newsItemClickListener != null){
                        ZhihuNewBean zhihuNewBean = (ZhihuNewBean) list.get(getAdapterPosition() - HEAD);
                        newsItemClickListener.onNewsItemClick(zhihuNewBean.getId());
                    }
                }
            });
        }
    }

    public interface newsItemClickListener{
        void onNewsItemClick(String newsid);
    }
}
