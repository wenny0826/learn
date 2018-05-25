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
import com.wenny.mvpdemo.entity.ZhihuNewBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/5/24.
 */

public class ZhihuListItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<ZhihuNewBean> zhihuNewBeans;

    public ZhihuListItemAdapter(Context context) {
        this.context = context;
        zhihuNewBeans = new ArrayList<>();
    }

    public void setZhihuNewBeans(List<ZhihuNewBean> zhihuNewBeans) {
        this.zhihuNewBeans = zhihuNewBeans;
        this.notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_zhihu_list_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.tv_title.setText(zhihuNewBeans.get(position).getTitle());
        if (zhihuNewBeans.get(position).getImages() != null){
            Glide.with(context).asBitmap().load(zhihuNewBeans.get(position).getImages().get(0)).into(viewHolder.cover);
        }
    }

    @Override
    public int getItemCount() {
        return zhihuNewBeans.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tv_title;
        ImageView cover;
        public ViewHolder(View itemView) {
            super(itemView);
            tv_title = itemView.findViewById(R.id.tv_title);
            cover = itemView.findViewById(R.id.cover);
        }
    }
}
