package com.daemon.newsapp.basepager;

import android.content.Context;
import android.view.Gravity;
import android.widget.TextView;

/**
 * Created by Chang on 04/10/16.
 */
public class SettingBaseTagPager extends BaseTagPager {
    public SettingBaseTagPager(Context context) {
        super(context);
    }
    @Override
    public void initData() {
        tv_title.setText("设置中心");

        TextView textView = new TextView(context);
        textView.setText("设置中心的内容");
        textView.setGravity(Gravity.CENTER);

        super.initData();
    }
}
