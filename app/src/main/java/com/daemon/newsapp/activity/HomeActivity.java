package com.daemon.newsapp.activity;

import android.app.Activity;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;

import com.daemon.newsapp.R;
import com.daemon.newsapp.view.HomeMenuFragment;
import com.daemon.newsapp.view.LeftMenuFragment;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;

public class HomeActivity extends SlidingFragmentActivity {

    private static final String LEFT_MENU_TAG = "leftMenuTag";
    private static final String HOME_MENU_TAG = "homeMenuTag";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initView();
        initData();
    }
    //初始化数据
    private void initData() {
       FragmentManager fragmentManager = getSupportFragmentManager();
        //获得事务
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        //完成替换:左侧 和 主
        transaction.replace(R.id.fl_left_menu_activity,new LeftMenuFragment(),LEFT_MENU_TAG);
        transaction.replace(R.id.fl_home_menu_activity,new HomeMenuFragment(),HOME_MENU_TAG);

        //提交事务
        transaction.commit();
    }

    private void initView() {
        //主界面
        setContentView(R.layout.activity_home);

        //左侧滑动界面
        setBehindContentView(R.layout.activity_left);
        //获取slidingmenu，并设置 左侧 效果模式
        SlidingMenu sm = getSlidingMenu();
        sm.setMode(SlidingMenu.LEFT);

        //设置触动模式--左边缘
        sm.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);

        //设置 侧滑后 主页面剩余的像素宽度
        sm.setBehindOffset(300);

    }
}
