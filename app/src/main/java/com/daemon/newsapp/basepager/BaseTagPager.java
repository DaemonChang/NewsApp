package com.daemon.newsapp.basepager;

import android.content.Context;

import android.view.View;
import android.view.animation.Animation;

import android.view.animation.RotateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageButton;

import android.widget.TextView;

import com.daemon.newsapp.R;
import com.daemon.newsapp.activity.HomeActivity;

/**用于填充ViewPager的基类，此基类的根布局含有标题布局 +新闻fragment
 * Created by Chang on 04/10/16.
 */
public class BaseTagPager {

    protected HomeActivity homeActivity;
    private View root;
    protected ImageButton ib_title_menu;
    protected TextView tv_title;
    protected FrameLayout fl_newsContent;

    public BaseTagPager(HomeActivity homeActivity){
        this.homeActivity = homeActivity;
        initView();//初始化界面
        initData();
        initEvent();
    }

    public void initEvent() {
        //菜单按钮图标 设置 事件
        ib_title_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //开启或 关闭 左侧滑菜单
                homeActivity.getSlidingMenu().toggle();

                doAnimation();//旋转一下
            }

        });


    }

    /**
     * 给菜单按钮设置动画
     */
    private void doAnimation() {
        RotateAnimation ra = new RotateAnimation(0,90,
                Animation.RELATIVE_TO_SELF,0.5f,
                Animation.RELATIVE_TO_SELF,0.5f);
        ra.setDuration(700);
        ra.setFillAfter(true);
        ra.setRepeatCount(1);
        ra.setRepeatMode(Animation.REVERSE);

        ib_title_menu.startAnimation(ra);

    }

    public void initData() {

    }

    public void initView() {
        //界面的根布局
        root = View.inflate(homeActivity , R.layout.fragment_content_home_base,null);

        ib_title_menu = (ImageButton) root.findViewById(R.id.ib_home_base_title_menu);

        tv_title = (TextView) root.findViewById(R.id.tv_home_base_title);

        fl_newsContent = (FrameLayout) root.findViewById(R.id.fl_home_base_news_content);

    }

    public View getRoot(){
        return  root ;
    }
}
