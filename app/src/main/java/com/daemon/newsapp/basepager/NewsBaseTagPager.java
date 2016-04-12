package com.daemon.newsapp.basepager;

import android.content.Context;
import android.util.Log;
import android.view.Gravity;
import android.widget.TextView;

import com.daemon.newsapp.activity.HomeActivity;
import com.daemon.newsapp.domain.NewsCenterData;
import com.daemon.newsapp.utils.MyConstants;
import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

/**
 * Created by Chang on 04/10/16.
 */
public class NewsBaseTagPager extends BaseTagPager {
    public NewsBaseTagPager(HomeActivity homeActivity) {
        super(homeActivity);
    }
    @Override
    public void initData() {
        //访问网络数据
        HttpUtils httpUtils = new HttpUtils();
        //6.0开始要在build.gradle的android中声明 useLibrary 'org.apache.http.legacy'
        httpUtils.send(HttpRequest.HttpMethod.GET, MyConstants.NEWSCENTERRL,
                new RequestCallBack<Object>() {
                    @Override
                    public void onSuccess(ResponseInfo<Object> responseInfo) {
                      String jsonData = (String) responseInfo.result;

                       // Log.d("@@json:",jsonData);
                        //解析json数据
                        parseData(jsonData);

                    }

                    @Override
                    public void onFailure(HttpException error, String msg) {

                    }
                });

        tv_title.setText("新闻中心");

        TextView textView = new TextView(homeActivity);
        textView.setText("新闻中心的内容");
        textView.setGravity(Gravity.CENTER);

        fl_newsContent.addView(textView);

        super.initData();
    }

    /**
     * 解释JSON数据
     * @param jsonData 从服务器获取到的json数据
     */
    private void parseData(String jsonData) {
        //google提供的开源解释json工具
        Gson gson = new Gson();
        //按NewsCenterData格式解释数据
        NewsCenterData newsCenterData = gson.fromJson(jsonData, NewsCenterData.class);
       // String title = newsCenterData.data.get(0).children.get(0).title;
        //Log.d("@@json: title",title); //北京

        //给左侧菜单栏LeftMenuFragment传递数据
        homeActivity.getLeftMenuFragment().setLeftMenuData(newsCenterData.data);

    }
}
