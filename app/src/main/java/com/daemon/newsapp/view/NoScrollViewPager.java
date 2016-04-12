package com.daemon.newsapp.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**无左右滑动功能的ViewPager
 * Created by Chang on 04/11/16.
 */
public class NoScrollViewPager extends ViewPager{
    public NoScrollViewPager(Context context) {
        super(context);
    }

    public NoScrollViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return false;//不让自己拦截
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return false;//不传递触摸事件
    }
}
