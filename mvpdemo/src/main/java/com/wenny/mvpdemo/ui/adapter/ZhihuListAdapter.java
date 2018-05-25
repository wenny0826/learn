package com.wenny.mvpdemo.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wenny.mvpdemo.R;
import com.wenny.mvpdemo.entity.ZhiHuListBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/5/24.
 */

public class ZhihuListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<ZhiHuListBean> zhiHuListBeans;

    public ZhihuListAdapter(Context context) {
        this.context = context;
        zhiHuListBeans = new ArrayList<>();
    }

    public void addData(ZhiHuListBean zhiHuListBean){
        this.zhiHuListBeans.add(zhiHuListBean);
        this.notifyItemRangeInserted(getItemCount() -1,1);
    }

    public List<ZhiHuListBean> getZhiHuListBeans() {
        return zhiHuListBeans;
    }
    public String getTitle(int position){
        return zhiHuListBeans.get(position).getShowData();
    }

    public void setData(ZhiHuListBean zhiHuListBean){
        this.zhiHuListBeans.clear();
        this.zhiHuListBeans.add(zhiHuListBean);
        this.notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_zhihu_list,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.tv_data.setText(zhiHuListBeans.get(position).getShowData());
        if (zhiHuListBeans.get(position).getStories() != null){
            ZhihuListItemAdapter zhihuListItemAdapter = new ZhihuListItemAdapter(context);
            viewHolder.recyclerview.setLayoutManager(new LinearLayoutManager(context));
            viewHolder.recyclerview.setAdapter(zhihuListItemAdapter);
            zhihuListItemAdapter.setZhihuNewBeans(zhiHuListBeans.get(position).getStories());
        }
    }

    @Override
    public int getItemCount() {
        return zhiHuListBeans.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tv_data;
        private RecyclerView recyclerview;
        public ViewHolder(View itemView) {
            super(itemView);
            tv_data = itemView.findViewById(R.id.tv_data);
            recyclerview = itemView.findViewById(R.id.recyclerview);
        }
    }
}
