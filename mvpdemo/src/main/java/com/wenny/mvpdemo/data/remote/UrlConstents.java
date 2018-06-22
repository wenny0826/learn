package com.wenny.mvpdemo.data.remote;

/**
 * Created by Administrator on 2018/5/23.
 */

public class UrlConstents {

    public static final String BASE_URL="https://news-at.zhihu.com/";
    public static final String GET_NEW_DATA="api/4/news/latest";
    public static final String GET_NEW_NEXt="api/4/news/before/{data}";
    public static final String GET_NEWS_INFO="api/4/news/{newsid}";//新闻详情
    public static final String GET_NEWS_OTHER_INFO="api/4/story-extra/{newsid}";//新闻详情_点赞
}
