package com.daemon.newsapp.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**SHaredPreferences工具
 * Created by Chang on 04/08/16.
 */
public class SpTools {

    /**
     * 通过SHaredPreferences保存配置信息 key -- value 键值对
     * @param context
     * @param key 关键字
     * @param value 值
     */
    public static void setBoolean(Context context , String key , boolean value){
       SharedPreferences sp =  context.getSharedPreferences(
               MyConstants.CONFIG_FILE,Context.MODE_PRIVATE);
       sp.edit().putBoolean(key,value).commit();//提交，保存数据

    }

    /**
     * 通过SHaredPreferences获取配置信息 key -- value 键值对
     * @param context
     * @param key 关键字
     * @param deValue 默认值（若找不到key，或key没有对应的值，则返回默认值）
     * @return
     */
    public static boolean getBoolean(Context context, String key , boolean deValue){
        SharedPreferences sp =  context.getSharedPreferences(
                MyConstants.CONFIG_FILE,Context.MODE_PRIVATE);
         return sp.getBoolean(key ,deValue);
    }
}
