package com.daemon.newsapp.basepager;

import android.content.Context;
import android.view.Gravity;
import android.widget.TextView;

/**
 * Created by Chang on 04/10/16.
 */
public class GovAffairsBaseTagPager extends BaseTagPager {
    public GovAffairsBaseTagPager(Context context) {
        super(context);
    }
    @Override
    public void initData() {
        tv_title.setText("政务");

        TextView textView = new TextView(context);
        textView.setText("政务的内容");
        textView.setGravity(Gravity.CENTER);

        super.initData();
    }
}
