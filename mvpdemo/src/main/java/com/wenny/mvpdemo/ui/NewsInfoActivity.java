package com.wenny.mvpdemo.ui;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v4.widget.NestedScrollView;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wenny.mvpdemo.R;
import com.wenny.mvpdemo.base.BaseActivity;
import com.wenny.mvpdemo.base.MyApplication;
import com.wenny.mvpdemo.data.entity.NewInfoExtraBean;
import com.wenny.mvpdemo.data.entity.NewsInfoBean;
import com.wenny.mvpdemo.presenter.NewsInfoContact;
import com.wenny.mvpdemo.presenter.NewsInfoPresenter;

/**
 * Created by Administrator on 2018/6/19.
 */

public class NewsInfoActivity extends BaseActivity implements NewsInfoContact.View {
    private String TAG = "NewsInfoActivity";
    private Context context;
    private NewsInfoPresenter presenter;
    private String newsId;

    private TextView tv_title, tv_watermark;
    private WebView webView;
    private ImageView image;
    private LinearLayout toolbar;
    private NestedScrollView nestedScrollview;
    private ConstraintLayout head;
    private int imageHeight,headY;
    private View top;
    @Override
    protected int getContentViewId() {
        return R.layout.activity_news_info;
    }

    @Override
    protected void init() {
        context = this;
        newsId = getIntent().getStringExtra("newsid");
        presenter = new NewsInfoPresenter(MyApplication.dataRepository);
        presenter.attachView(this);
        presenter.getData(newsId);

        tv_title = findViewById(R.id.tv_title);
        tv_watermark = findViewById(R.id.tv_watermark);
        webView = findViewById(R.id.webView);
        image = findViewById(R.id.image);
        toolbar = findViewById(R.id.toolbar);
        nestedScrollview = findViewById(R.id.nestedScrollview);
        head = findViewById(R.id.head);
        top = findViewById(R.id.top);

        //声明WebSettings子类
        WebSettings webSettings = webView.getSettings();
        // 如果访问的页面中要与Javascript交互，则webview必须设置支持Javascript
        webSettings.setJavaScriptEnabled(true);
        //设置自适应屏幕，两者合用
        webSettings.setUseWideViewPort(true); //将图片调整到适合webview的大小
        webSettings.setLoadWithOverviewMode(true); // 缩放至屏幕的大小

        image.post(new Runnable() {
            @Override
            public void run() {
                imageHeight = image.getMeasuredHeight();
                headY = top.getMeasuredHeight();
            }
        });
        nestedScrollview.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                Log.d(TAG, "onScrollChange: " + scrollY +" "+ imageHeight);
                if (scrollY <= imageHeight){
                    toolbar.setAlpha((float) (imageHeight-scrollY)/(float) imageHeight);
                    head.setY(scrollY/2 + headY);
                }else {
                    toolbar.setAlpha(0f);
                }
            }
        });
    }

    @Override
    public void getData(NewsInfoBean newsInfoBean) {
        webView.loadDataWithBaseURL(null, newsInfoBean.getBody(), "text/html", "utf-8", null);
        Glide.with(context).load(newsInfoBean.getImage()).into(image);
        tv_title.setText(newsInfoBean.getTitle());
        tv_watermark.setText(newsInfoBean.getImage_source());
    }

    @Override
    public void getNewsExtra(NewInfoExtraBean newInfoExtraBean) {

    }
}
