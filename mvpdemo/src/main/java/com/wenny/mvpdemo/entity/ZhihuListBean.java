package com.wenny.mvpdemo.entity;

import com.wenny.mvpdemo.util.TimeUtil;

import java.util.List;

/**
 * Created by Administrator on 2018/5/24.
 */

public class ZhiHuListBean {
    private String date;
    private List<ZhihuNewBean> stories;

    public String getDate() {
        return date;
    }

    public String getShowData() {
        if (date.equals("今日热点")){
            return date;
        }
        long time = TimeUtil.string2Date(date, TimeUtil.DATE_FORMAT_13).getTime();
        return TimeUtil.date2String(time,TimeUtil.DATE_FORMAT_12)+" " +TimeUtil.getWeekTime(time);
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<ZhihuNewBean> getStories() {
        return stories;
    }

    public void setStories(List<ZhihuNewBean> stories) {
        this.stories = stories;
    }
}
