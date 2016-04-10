package com.daemon.newsapp.view;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.daemon.newsapp.R;
import com.daemon.newsapp.activity.HomeActivity;

/**
 *主界面 和 侧滑 界面的 基类
 */
public abstract class BaseFragment extends Fragment {
    protected HomeActivity currentActivity;//fragment所在的上下文

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        currentActivity = (HomeActivity) getActivity();//获取上下文

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = initView();
        return root;
    }

    /**
     * 因为两个fragment的view都不一样，因此抽象一个initView方法，
     * 用于onCreateView()生成相应的view。
     * 子类必须覆盖此方法
     */
    public abstract View initView();

    /**
     * 由于两个fragment的数据和事件的需求都不一致，因此不定义为抽象方法
     * 而是，需要的时候才覆盖。
     * 子类覆盖此方法初始化事件
     */
    public void initEvent() {

    }

    /**
     * 子类覆盖此方法来初始化数据
     */
    public void initData() {

    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initData();
        initEvent();
    }

}
