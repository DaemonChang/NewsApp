package com.daemon.newsapp.basepager;

import android.content.Context;
import android.view.Gravity;
import android.widget.TextView;

import com.daemon.newsapp.activity.HomeActivity;

/**
 * Created by Chang on 04/10/16.
 */
public class GovAffairsBaseTagPager extends BaseTagPager {
    public GovAffairsBaseTagPager(HomeActivity homeActivity) {
        super(homeActivity);
    }
    @Override
    public void initData() {
        tv_title.setText("政务");

        TextView textView = new TextView(homeActivity);
        textView.setText("政务的内容");
        textView.setGravity(Gravity.CENTER);

        fl_newsContent.addView(textView);

        super.initData();
    }
}
