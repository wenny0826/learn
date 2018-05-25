package com.wenny.mvpdemo.evenbus;

/**
 * Created by Administrator on 2018/5/25.
 */

public class ChangeTitleEven {
    private String title;

    public ChangeTitleEven(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
