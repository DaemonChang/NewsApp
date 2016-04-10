package com.daemon.newsapp.view;

import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Chang on 04/10/16.
 */
public class LeftMenuFragment extends BaseFragment {
    @Override
    public View initView() {
        TextView textView = new TextView(currentActivity);
        textView.setText("左侧菜单");
        textView.setGravity(Gravity.CENTER);
        return textView;
    }
}
