package com.wenny.mvpdemo.data.entity;

import com.wenny.mvpdemo.util.StringConverter;

import org.greenrobot.greendao.annotation.Convert;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;

import java.util.List;

/**
 * Created by Administrator on 2018/5/23.
 */
@Entity(nameInDb = "news")
public class ZhihuNewBean {

    /**
     * images : ["https://pic2.zhimg.com/v2-e9c9c297487ebe19301b1ff0bef8a041.jpg"]
     * type : 0
     * id : 9683739
     * ga_prefix : 052306
     * title : 瞎扯 · 如何正确地吐槽
     */

    private String date;
    private int type;
    @Id@NotNull
    private String id;
    private String ga_prefix;
    private String title;
    @Convert(columnType = String.class, converter = StringConverter.class)
    private List<String> images;
    @Generated(hash = 2071650646)
    public ZhihuNewBean(String date, int type, @NotNull String id, String ga_prefix,
            String title, List<String> images) {
        this.date = date;
        this.type = type;
        this.id = id;
        this.ga_prefix = ga_prefix;
        this.title = title;
        this.images = images;
    }
    @Generated(hash = 2125355332)
    public ZhihuNewBean() {
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
    public List<String> getImages() {
        return this.images;
    }
    public void setImages(List<String> images) {
        this.images = images;
    }
    public String getDate() {
        return this.date;
    }
    public void setDate(String date) {
        this.date = date;
    }

}
