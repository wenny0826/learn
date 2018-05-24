package com.wenny.mvpdemo.entity;

import java.util.List;

/**
 * Created by Administrator on 2018/5/23.
 */

public class ZhiHuHomeBean {
    private String date;
    private List<ZhihuListBean> stories;
    private List<ZhihuBanberBean> top_stories;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<ZhihuListBean> getStories() {
        return stories;
    }

    public void setStories(List<ZhihuListBean> stories) {
        this.stories = stories;
    }

    public List<ZhihuBanberBean> getTop_stories() {
        return top_stories;
    }

    public void setTop_stories(List<ZhihuBanberBean> top_stories) {
        this.top_stories = top_stories;
    }
}
