package com.daemon.newsapp.newstpipage;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.daemon.newsapp.R;
import com.daemon.newsapp.activity.HomeActivity;
import com.daemon.newsapp.domain.NewsCenterData;
import com.daemon.newsapp.domain.NewsCenterData.NewsData.ViewTagData;
import com.daemon.newsapp.domain.NewsTpiNewsData;
import com.daemon.newsapp.utils.MyConstants;
import com.daemon.newsapp.utils.SpTools;
import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.lidroid.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

/**新闻-->左侧菜单-->新闻--显示的页面
 * Created by Chang on 04/16/16.
 */
public class NewsTpiNewspage {
    //组件
    @ViewInject(R.id.tpi_vp_lunbo_pic)
    private ViewPager vp_lunbo_pic;//轮播图
    @ViewInject(R.id.tpi_tv_lunbo_title)
    private TextView tv_lunbo_title;//轮播图对应的文字
    @ViewInject(R.id.tpi_ll_points)
    private LinearLayout ll_points;//轮播图对应的点组合
    @ViewInject(R.id.tpi_lv_news)
    private ListView lv_news;//新闻列表

    //数据
    private HomeActivity homeActivity;
    private View root;
    private ViewTagData viewTagData; //页签对应的数据
    private Gson gson;
    private String jsonCache;
    private List<NewsTpiNewsData.NewsTpiNewsData_Data_Topnews> lunboDatas =
            new ArrayList<>();//轮播图的数据,先给个空的容器，以免空指针异常
    private LunboAdapter lunboAdapter;


    public NewsTpiNewspage(HomeActivity homeActivity, ViewTagData viewTagData) {
        this.homeActivity = homeActivity;
        this.viewTagData = viewTagData;
        gson = new Gson();
        initView();
        initData();
        initEvent();
    }

    private void initEvent() {

    }

    private void initData() {
        //创建适配器
        lunboAdapter = new LunboAdapter();
        //给viewpager
        vp_lunbo_pic.setAdapter(lunboAdapter);
    //轮播图的数据
        //新闻列表数据
        //先从本地获取数据
        jsonCache = SpTools.getString(homeActivity,viewTagData.url,"");
        if(!TextUtils.isEmpty(jsonCache)){//有缓存数据
            NewsTpiNewsData newsData = parseData(jsonCache);//解析数据
            //处理数据
            processData(newsData);
        }
        getDataFromNet();//从网络获取数据


    }

    /**
     * 处理数据
     * @param newsData
     */
    private void processData(NewsTpiNewsData newsData) {

        //1.设置轮播图的数据
        setLunBoData(newsData);
    }

    /**
     * 设置轮播图的数据
     * @param newsData
     */
    private void setLunBoData(NewsTpiNewsData newsData) {
        //获取轮播图数据
        lunboDatas = newsData.data.topnews;
        //有可能获取两次来更新数据，因此要刷新数据
        lunboAdapter.notifyDataSetChanged();
    }

    /**
     * 轮播图的适配器
     */
    private class LunboAdapter extends PagerAdapter{

        @Override
        public Object instantiateItem(ViewGroup container, int position) {


            return super.instantiateItem(container, position);
        }

        @Override
        public int getCount() {
            return lunboDatas.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }

    /**
     * 解析json数据
     */
    private NewsTpiNewsData parseData(String jsonData) {
        NewsTpiNewsData newsTpiNewsData = gson.fromJson(jsonData,NewsTpiNewsData.class);
        //Log.d("@@jsonData",newsTpiNewsData.data.news.get(0).title);
        return newsTpiNewsData;
    }

    /**
     * 从网络获取数据
     */
    public void getDataFromNet() {


        HttpUtils httpUtils = new HttpUtils();
        httpUtils.send(HttpRequest.HttpMethod.GET, MyConstants.SERVERURL + viewTagData.url
                , new RequestCallBack<String>() {
                    @Override
                    public void onSuccess(ResponseInfo<String> responseInfo) {
                        String jsonData = responseInfo.result;
                        NewsTpiNewsData data =  parseData(jsonData);//解释数据
                        //处理数据
                        processData(data);
                        //保存数据到本地--缓存
                        SpTools.setString(homeActivity,viewTagData.url,jsonData);
                    }

                    @Override
                    public void onFailure(HttpException e, String s) {

                    }
                });


    }

    private void initView() {
        //页签对应页面的根布局
        root = View.inflate(homeActivity, R.layout.tpi_news_content,null);
        //动态注入
        ViewUtils.inject(this,root);
    }

    public View getRootView(){
        return root;
    }


}
