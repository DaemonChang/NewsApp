package com.daemon.newsapp.newscenterbasepage;

import android.view.View;

import com.daemon.newsapp.activity.HomeActivity;

/**左侧栏目的基类
 * Created by Chang on 04/13/16.
 */
public abstract class NewsBasePage {
    protected HomeActivity baseActivity;
    private View root; //根布局

    public NewsBasePage(HomeActivity homeActivity) {
            baseActivity = homeActivity;
            root = initView();
            initEvent();
    }
    public View getRoot(){
        return root;
    }

    /**
     * 子类覆盖此方法来完成事件的添加
     */
    public void initEvent(){

    }
    /**
     * 子类覆盖此方法来完成数据的显示
     */
    public void initData(){

    }

    /**
     * 子类必须覆盖此方法来显示自定义的view
     * @return
     */
    public abstract View initView();
}
