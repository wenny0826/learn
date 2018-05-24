package com.wenny.mvpdemo.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.wenny.mvpdemo.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

/**
 * Created by ${wenny} on 2017/6/21.
 */

public  abstract class BaseActivity extends AppCompatActivity implements BaseView{
    // 管理运行的所有的activity
//    public final static List<AppCompatActivity> mActivities = new LinkedList<AppCompatActivity>();
//    public static BaseActivity activity;
    private FragmentManager fragmentManager;

    //当前正在展示的Fragment
    private BaseFragment showFragment;
    private Toast toast;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //使输入框不被软键盘挡住
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        setContentView(getContentViewId());

        //注册EventBus
        EventBus.getDefault().register(this);
        //获得FragmentManager对象
        fragmentManager = getSupportFragmentManager();

        init();
        bindListener();
        loadDatas();
        //友盟推送统计应用启动次数
//        PushAgent.getInstance(this).onAppStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
//        activity = this;
    }
    @Override
    protected void onPause() {
        super.onPause();
//        activity = null;
    }

    /**
     * 获得要显示的布局ID
     *
     * @return
     */
    protected abstract int getContentViewId();

    /**
     * 初始化
     */
    protected void init() {}

    /**
     * 加载数据
     */
    protected void loadDatas() {}

    /**
     * 绑定监听
     */
    protected void bindListener() {}

    protected void showToast(Context context, String str) {
        if (toast == null) {
            toast = Toast.makeText(context, str, Toast.LENGTH_SHORT);
        } else {
            toast.setText(str);
        }
        toast.show();
    }

    /**
     * EventBus的接收数据
     *
     * @param value
     */
    @Subscribe
    public void onEvent(Object value) {}


    /**
     * 以动画的方式启动activity
     *
     * @param intent
     * @param animinid
     * @param animoutid
     */
    public void startActivityForAnimation(Intent intent, int animinid, int animoutid) {
        startActivity(intent);
        overridePendingTransition(animinid, animoutid);
    }

    /**
     * 展示Fragment
     */
    protected void showFragment(int resid, BaseFragment fragment) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        //隐藏正在暂时的Fragment
        if (showFragment != null) {
            fragmentTransaction.hide(showFragment);
        }

        //展示需要显示的Fragment对象
        Fragment mFragment = fragmentManager.findFragmentByTag(fragment.getClass().getName());
        if (mFragment != null) {
            fragmentTransaction.show(mFragment);
            showFragment = (BaseFragment) mFragment;
        } else {
            fragmentTransaction.add(resid, fragment, fragment.getClass().getName());
            showFragment = fragment;
        }

        fragmentTransaction.commit();
    }

    /**
     * 启动Activity 可以携带Bundle ,选择是否Finish
     */
    public void startActivity(Class<? extends Activity> target, Bundle bundle, boolean finish) {
        Intent intent = new Intent();
        intent.setClass(this, target);
        if (bundle != null)
            intent.putExtra(getPackageName(), bundle);
        startActivity(intent);
        if (finish)
            finish();
    }

    /**
     * 获取上面方法携带的Bundle bundle名为包名
     */
    public Bundle getBundle() {
        if (getIntent() != null && getIntent().hasExtra(getPackageName()))
            return getIntent().getBundleExtra(getPackageName());
        else
            return null;
    }


    /**
     * 关闭当前activity
     *
     * @param view
     */
    public void finishSelf(View view) {
        this.finish();
        this.overridePendingTransition(0, R.anim.finish_activity_anim);

    }

    /**
     * 侧滑关闭activity
     */
    public void touchFinish() {
        super.finish();
        overridePendingTransition(R.anim.alpha_enter, R.anim.alpha_exit);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        //注销EventBus
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showErr() {

    }

    @Override
    public Context getContext() {
        return BaseActivity.this;
    }
}
