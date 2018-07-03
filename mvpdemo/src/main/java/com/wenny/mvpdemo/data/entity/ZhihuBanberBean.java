package com.wenny.mvpdemo.data.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Administrator on 2018/5/23.
 */
@Entity(nameInDb = "news_banner")
public class ZhihuBanberBean {

    /**
     * image : https://pic2.zhimg.com/v2-d1c570786cbb2969865b9081c1209e81.jpg
     * type : 0
     * id : 9683775
     * ga_prefix : 052313
     * title : 10 万美元一稿不改，乔布斯凭什么还选择他设计的 Logo？
     */
    private String date;
    private String image;
    private int type;
    @Id
    private String id;
    private String ga_prefix;
    private String title;
    @Generated(hash = 834971573)
    public ZhihuBanberBean(String date, String image, int type, String id,
            String ga_prefix, String title) {
        this.date = date;
        this.image = image;
        this.type = type;
        this.id = id;
        this.ga_prefix = ga_prefix;
        this.title = title;
    }
    @Generated(hash = 195600353)
    public ZhihuBanberBean() {
    }
    public String getImage() {
        return this.image;
    }
    public void setImage(String image) {
        this.image = image;
    }
    public int getType() {
        return this.type;
    }
    public void setType(int type) {
        this.type = type;
    }
    public String getId() {
        return this.id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getGa_prefix() {
        return this.ga_prefix;
    }
    public void setGa_prefix(String ga_prefix) {
        this.ga_prefix = ga_prefix;
    }
    public String getTitle() {
        return this.title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getDate() {
        return this.date;
    }
    public void setDate(String date) {
        this.date = date;
    }

}
