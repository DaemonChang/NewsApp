package com.daemon.newsapp.newscenterbasepage;

import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.daemon.newsapp.activity.HomeActivity;

/**组图栏目
 * Created by Chang on 04/13/16.
 */
public class PhotosNewsBasePage extends NewsBasePage {
    public PhotosNewsBasePage(HomeActivity homeActivity) {
        super(homeActivity);
    }

    @Override
    public View initView() {
        TextView textView = new TextView(baseActivity);
        textView.setText("组图内容");
        textView.setGravity(Gravity.CENTER);
        return textView;
    }
}
