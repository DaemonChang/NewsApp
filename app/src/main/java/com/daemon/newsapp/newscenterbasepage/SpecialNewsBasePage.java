package com.daemon.newsapp.newscenterbasepage;

import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.daemon.newsapp.activity.HomeActivity;

/**专题栏目
 * Created by Chang on 04/13/16.
 */
public class SpecialNewsBasePage extends NewsBasePage {
    public SpecialNewsBasePage(HomeActivity homeActivity) {
        super(homeActivity);
    }

    @Override
    public View initView() {
        TextView textView = new TextView(baseActivity);
        textView.setText("专题内容");
        textView.setGravity(Gravity.CENTER);
        return textView;
    }
}
