package com.daemon.newsapp.newscenterbasepage;

import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.daemon.newsapp.activity.HomeActivity;

/**新闻栏目
 * Created by Chang on 04/13/16.
 */
public class NewsNewsBasePage extends NewsBasePage {
    public NewsNewsBasePage(HomeActivity homeActivity) {
        super(homeActivity);
    }

    @Override
    public View initView() {
        TextView textView = new TextView(baseActivity);
        textView.setText("新闻内容");
        textView.setGravity(Gravity.CENTER);
        return textView;
    }
}
