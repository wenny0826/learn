package com.wenny.mvpdemo.ui;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.wenny.mvpdemo.R;
import com.wenny.mvpdemo.ui.base.BaseActivity;

/**
 * Created by Administrator on 2018/5/15.
 */

public class MainActivity extends BaseActivity {
    private String TAG = "MainActivity";
    private BottomNavigationBar bottom_view;
    private DrawerLayout drawerLayout;
    private NavigationView navigation;
    private Toolbar toolbar;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    protected void init() {
        bottom_view = findViewById(R.id.bottom_view);
        drawerLayout = findViewById(R.id.drawerLayout);
        navigation = findViewById(R.id.navigation);
        toolbar = findViewById(R.id.toolbar);
        bottom_view.addItem(new BottomNavigationItem(R.drawable.ic_launcher_background, "one"))
                .addItem(new BottomNavigationItem(R.drawable.ic_launcher_background, "two"))
                .addItem(new BottomNavigationItem(R.drawable.ic_launcher_background, "three"))
                .addItem(new BottomNavigationItem(R.drawable.ic_launcher_background, "four"))
                .setMode(BottomNavigationBar.MODE_FIXED)
                .setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC)
                .setFirstSelectedPosition(0)
                .initialise();

        bottom_view.setTabSelectedListener(new BottomNavigationBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position) {
                switch (position) {
                    case 0:
                        showFragment(R.id.content, new ZhiHuFragment());
                        break;
                    case 1:
                        showFragment(R.id.content, new TwoFragment());
                        break;
                    case 2:
                        showFragment(R.id.content, new ThreeFragment());
                        break;
                    case 3:
                        showFragment(R.id.content, new FourFragment());
                        break;
                }
            }

            @Override
            public void onTabUnselected(int position) {

            }

            @Override
            public void onTabReselected(int position) {

            }
        });

        toolbar.setNavigationOnClickListener(v -> {
            if (drawerLayout.isDrawerOpen(navigation)) {
                drawerLayout.closeDrawer(navigation);
            } else {
                drawerLayout.openDrawer(navigation);
            }
        });
        navigation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                drawerLayout.closeDrawer(navigation);
                return true;
            }
        });
    }
}
