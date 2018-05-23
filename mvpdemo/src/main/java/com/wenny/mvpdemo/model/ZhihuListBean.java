package com.wenny.mvpdemo.model;

import java.util.List;

/**
 * Created by Administrator on 2018/5/23.
 */
public class ZhihuListBean {

    /**
     * images : ["https://pic2.zhimg.com/v2-e9c9c297487ebe19301b1ff0bef8a041.jpg"]
     * type : 0
     * id : 9683739
     * ga_prefix : 052306
     * title : 瞎扯 · 如何正确地吐槽
     */

    private int type;
    private String id;
    private String ga_prefix;
    private String title;
    private List<String> images;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGa_prefix() {
        return ga_prefix;
    }

    public void setGa_prefix(String ga_prefix) {
        this.ga_prefix = ga_prefix;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }
}
