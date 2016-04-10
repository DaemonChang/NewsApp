package com.daemon.newsapp.basepager;

import android.content.Context;
import android.view.Gravity;
import android.widget.TextView;

/**
 * Created by Chang on 04/10/16.
 */
public class ServiceBaseTagPager extends BaseTagPager {
    public ServiceBaseTagPager(Context context) {
        super(context);
    }
    @Override
    public void initData() {
        tv_title.setText("智慧服务");

        TextView textView = new TextView(context);
        textView.setText("智慧服务的内容");
        textView.setGravity(Gravity.CENTER);

        super.initData();
    }
}
