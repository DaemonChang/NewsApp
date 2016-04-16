package com.daemon.newsapp.basepager;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.daemon.newsapp.activity.HomeActivity;
import com.daemon.newsapp.domain.NewsCenterData;
import com.daemon.newsapp.newscenterbasepage.InteractionNewsBasePage;
import com.daemon.newsapp.newscenterbasepage.NewsBasePage;
import com.daemon.newsapp.newscenterbasepage.NewsNewsBasePage;
import com.daemon.newsapp.newscenterbasepage.PhotosNewsBasePage;
import com.daemon.newsapp.newscenterbasepage.SpecialNewsBasePage;
import com.daemon.newsapp.utils.MyConstants;
import com.daemon.newsapp.utils.SpTools;
import com.daemon.newsapp.view.LeftMenuFragment;
import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chang on 04/10/16.
 */
public class NewsBaseTagPager extends BaseTagPager {
    //新闻中心要显示的四个栏目对应页面的容器
    private List<NewsBasePage> newsBasePages = new ArrayList<>();
    private NewsCenterData newsCenterData;
    private Gson gson;


    public NewsBaseTagPager(HomeActivity homeActivity) {
        super(homeActivity);

    }
    @Override
    public void initData() {
            //第一步：访问网络数据
            HttpUtils httpUtils = new HttpUtils();
            //6.0开始要在build.gradle的android中声明 useLibrary 'org.apache.http.legacy'
            httpUtils.send(HttpRequest.HttpMethod.GET, MyConstants.NEWSCENTERURL,
                    new RequestCallBack<Object>() {
                        @Override
                        public void onStart() {
                            //第0步，访问本地缓存数据
                            String jsonCache = SpTools.getString(homeActivity,MyConstants.NEWSCENTERURL,"");
                            if(!TextUtils.isEmpty(jsonCache)){
                                //Log.d("@@jsonCache::",jsonCache);
                                 //若缓存不为空
                                parseData(SpTools.getString(homeActivity,MyConstants.NEWSCENTERURL,""));
                            }
                            super.onStart();
                        }

                        @Override
                        public void onSuccess(ResponseInfo<Object> responseInfo) {
                            String jsonData = (String) responseInfo.result;

                            //保存一份于本地 作为缓存
                            SpTools.setString(homeActivity, MyConstants.NEWSCENTERURL, jsonData);

                            // Log.d("@@json:",jsonData);
                            //第二步：解析json数据
                            parseData(jsonData);

                        }

                        @Override
                        public void onFailure(HttpException error, String msg) {
//                             parseData(SpTools.getString(homeActivity,MyConstants.NEWSCENTERRL,""));

                        }
                    });



        super.initData();
    }

    /**
     * 解释JSON数据
     * @param jsonData 从服务器获取到的json数据
     */
    private void parseData(String jsonData) {
        //google提供的开源解释json工具
        if(gson == null)
          gson = new Gson();
        //按NewsCenterData格式解释数据
        newsCenterData = gson.fromJson(jsonData, NewsCenterData.class);
        String title = newsCenterData.data.get(0).children.get(0).title;
        //Log.d("@@json: title",title); //北京

        //给左侧菜单栏LeftMenuFragment传递数据
        homeActivity.getLeftMenuFragment().setLeftMenuData(newsCenterData.data);
 /* //给左侧菜单栏设置监听事件
        homeActivity.getLeftMenuFragment().setOnSwitchPageListener(new LeftMenuFragment.OnSwitchPageListener() {
            @Override
            public void switchPage(int selectIndex) {
                switchNewsPage(selectIndex);
            }
        });
*/
        //第三步：处理数据
        //读取的数据传封装到界面容器中，通过左侧菜单栏点击，显示对应的页面
        //根据服务器的数据，创建四个页面（按顺序）
        for(NewsCenterData.NewsData newsData : newsCenterData.data){
            //遍历四个左侧栏对应的页面
            NewsBasePage newsBasePage = null;//基类的引用
            switch (newsData.type){
                case 1://新闻页面
                    newsBasePage = new NewsNewsBasePage(homeActivity,newsCenterData.data.get(0).children);
                    break;
                case 10://专题页面
                    newsBasePage = new SpecialNewsBasePage(homeActivity);
                    break;
                case 2://组图页面
                    newsBasePage = new PhotosNewsBasePage(homeActivity);
                    break;
                case 3://互动页面
                    newsBasePage = new InteractionNewsBasePage(homeActivity);
                    break;

            }//switch
            //添加新闻中心的页面到容器中
            newsBasePages.add(newsBasePage);
        }//for
        //默认选择第一个页面：新闻页面
        switchNewsPage(0);
    }

    /**
     *动态显示选中的页面
     * @param position 第几个左侧菜单标签
     */
    @Override
    public void switchNewsPage(int position){
        NewsBasePage newsBasePage = newsBasePages.get(position);
        //显示标题
        tv_title.setText(newsCenterData.data.get(position).title);
        //擦干净白纸（清理上次显示的页面）
        fl_newsContent.removeAllViews();

        //初始化数据
        newsBasePage.initData();
        //替换白纸，显示对应页面的根布局
        fl_newsContent.addView(newsBasePage.getRoot());
    }
}
