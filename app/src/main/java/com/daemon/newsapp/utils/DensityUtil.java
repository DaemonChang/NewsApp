package com.daemon.newsapp.utils;

import android.content.Context;
import android.util.Log;

/**
 * Created by Chang on 04/09/16.
 */
public class DensityUtil {
    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dp2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        Log.d("@@scale",+scale+"");//   当前屏幕的dpi/160dpi
        return (int) (dpValue * scale + 0.5f);  // +0.5f是为了四舍五入
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dp(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }
}
