package com.daemon.newsapp.newscenterbasepage;

import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.daemon.newsapp.activity.HomeActivity;

/**互动栏目
 * Created by Chang on 04/13/16.
 */
public class InteractionNewsBasePage extends NewsBasePage {
    public InteractionNewsBasePage(HomeActivity homeActivity) {
        super(homeActivity);
    }

    @Override
    public View initView() {
        TextView textView = new TextView(baseActivity);
        textView.setText("互动内容");
        textView.setGravity(Gravity.CENTER);
        return textView;
    }
}
