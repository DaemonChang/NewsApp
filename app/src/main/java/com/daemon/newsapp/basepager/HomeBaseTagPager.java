package com.daemon.newsapp.basepager;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.daemon.newsapp.activity.HomeActivity;

/**
 * Created by Chang on 04/10/16.
 */
public class HomeBaseTagPager extends BaseTagPager {
    public HomeBaseTagPager(HomeActivity homeActivity) {
        super(homeActivity);
    }

    @Override
    public void initData() {
        //屏蔽侧滑菜单按钮
        ib_title_menu.setVisibility(View.GONE);
       tv_title.setText("首页");

        TextView textView = new TextView(homeActivity);
        textView.setText("首页的内容");
        textView.setGravity(Gravity.CENTER);

        fl_newsContent.addView(textView);
        super.initData();
    }
}
