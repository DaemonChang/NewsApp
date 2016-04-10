package com.daemon.newsapp.basepager;

import android.content.Context;
import android.view.Gravity;
import android.widget.TextView;

/**
 * Created by Chang on 04/10/16.
 */
public class NewsBaseTagPager extends BaseTagPager {
    public NewsBaseTagPager(Context context) {
        super(context);
    }
    @Override
    public void initData() {
        tv_title.setText("新闻中心");

        TextView textView = new TextView(context);
        textView.setText("新闻中心的内容");
        textView.setGravity(Gravity.CENTER);

        super.initData();
    }
}
