package com.daemon.newsapp.basepager;

import android.content.Context;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;

import android.widget.TextView;

import com.daemon.newsapp.R;

/**用于填充ViewPager的基类，此基类的根布局含有标题布局 +新闻fragment
 * Created by Chang on 04/10/16.
 */
public class BaseTagPager {

    protected Context context;
    private View root;
    protected ImageButton ib_title_menu;
    protected TextView tv_title;
    protected FrameLayout fl_newsContent;

    public BaseTagPager(Context context){
        this.context = context;
        initView();//初始化界面
        initData();
        initEvent();
    }

    public void initEvent() {

    }

    public void initData() {

    }

    public void initView() {
        //界面的根布局
        root = View.inflate(context , R.layout.fragment_content_home_base,null);

        ib_title_menu = (ImageButton) root.findViewById(R.id.ib_home_base_title_menu);

        tv_title = (TextView) root.findViewById(R.id.tv_home_base_title);

        fl_newsContent = (FrameLayout) root.findViewById(R.id.fl_home_base_news_content);

    }

    public View getRoot(){
        return  root ;
    }
}
