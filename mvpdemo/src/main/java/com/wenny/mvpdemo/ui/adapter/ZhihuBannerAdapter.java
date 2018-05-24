package com.wenny.mvpdemo.ui.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wenny.mvpdemo.R;
import com.wenny.mvpdemo.entity.ZhihuBanberBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/5/23.
 */

public class ZhihuBannerAdapter extends PagerAdapter {
    private List<ZhihuBanberBean> zhihuBanberBeans;
    private List<FrameLayout> frameLayouts;
    private Context context;

    public ZhihuBannerAdapter(Context context) {
        this.context = context;
        zhihuBanberBeans = new ArrayList<>();
        frameLayouts = new ArrayList<>();
    }

    public void setZhihuBanberBeans(List<ZhihuBanberBean> zhihuBanberBeans) {
        this.zhihuBanberBeans = zhihuBanberBeans;
        for (ZhihuBanberBean zhihuBanberBean : zhihuBanberBeans) {
            FrameLayout frameLayout = new FrameLayout(context);
            ImageView imageView = new ImageView(context);
            TextView textView = new TextView(context);
            Glide.with(context).asBitmap().load(zhihuBanberBean.getImage()).into(imageView);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            textView.setTextColor(Color.WHITE);
            textView.setTextSize(20);
            textView.setText(zhihuBanberBean.getTitle());
            ViewGroup.LayoutParams layoutParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, Gravity.BOTTOM);
            textView.setPadding(30,20,30,50);
            View view = new View(context);
            view.setBackgroundColor(context.getResources().getColor(R.color.image_top));
            ViewGroup.LayoutParams layoutParams1 = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

            frameLayout.addView(imageView);
            frameLayout.addView(view,layoutParams1);
            frameLayout.addView(textView,layoutParams);
            frameLayouts.add(frameLayout);
        }
        this.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return zhihuBanberBeans.size();
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        container.addView(frameLayouts.get(position));
        return frameLayouts.get(position);
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(frameLayouts.get(position));
    }

}
