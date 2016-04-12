package com.daemon.newsapp.basepager;

import android.content.Context;
import android.view.Gravity;
import android.widget.TextView;

import com.daemon.newsapp.activity.HomeActivity;

/**
 * Created by Chang on 04/10/16.
 */
public class ServiceBaseTagPager extends BaseTagPager {
    public ServiceBaseTagPager(HomeActivity homeActivity) {
        super(homeActivity);
    }
    @Override
    public void initData() {
        tv_title.setText("智慧服务");

        TextView textView = new TextView(homeActivity);
        textView.setText("智慧服务的内容");
        textView.setGravity(Gravity.CENTER);

        fl_newsContent.addView(textView);
        super.initData();
    }
}
