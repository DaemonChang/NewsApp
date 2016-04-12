package com.daemon.newsapp.activity;

import android.app.Activity;
import android.support.v4.app.Fragment;
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

        //在HomeMenuFragment处按需设置了，因此此处取消设置
        //设置触动模式-
        //sm.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        sm.setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);//修复：初次安装，首页能滑动左侧菜单

        //设置 侧滑后 主页面剩余的像素宽度
        sm.setBehindOffset(300);

    }
    /**
     * @return 返回主页面的Fragment
     */
    public HomeMenuFragment getHomeMenuFragment(){
        FragmentManager fm = getSupportFragmentManager();
        HomeMenuFragment homeMenuFragment = (HomeMenuFragment) fm.findFragmentByTag(HOME_MENU_TAG);
        return  homeMenuFragment;
    }

    /**
     * @return 返回左侧菜单的Fragment
     */
    public LeftMenuFragment getLeftMenuFragment(){
        FragmentManager fm = getSupportFragmentManager();
        LeftMenuFragment leftMenuFragment = (LeftMenuFragment) fm.findFragmentByTag(LEFT_MENU_TAG);
        return  leftMenuFragment;
    }
}
