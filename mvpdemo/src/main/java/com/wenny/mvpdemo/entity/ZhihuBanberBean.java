package com.wenny.mvpdemo.entity;

/**
 * Created by Administrator on 2018/5/23.
 */

public class ZhihuBanberBean {

    /**
     * image : https://pic2.zhimg.com/v2-d1c570786cbb2969865b9081c1209e81.jpg
     * type : 0
     * id : 9683775
     * ga_prefix : 052313
     * title : 10 万美元一稿不改，乔布斯凭什么还选择他设计的 Logo？
     */

    private String image;
    private int type;
    private String id;
    private String ga_prefix;
    private String title;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

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
}
