package com.daemon.newsapp.newscenterbasepage;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.TintableBackgroundView;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.daemon.newsapp.R;
import com.daemon.newsapp.activity.HomeActivity;
import com.daemon.newsapp.domain.NewsCenterData;
import com.daemon.newsapp.domain.NewsCenterData.NewsData.ViewTagData;
import com.daemon.newsapp.newstpipage.NewsTpiNewspage;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.viewpagerindicator.TabPageIndicator;

import java.util.ArrayList;
import java.util.List;

/**新闻栏目
 * Created by Chang on 04/13/16.
 */
public class NewsNewsBasePage extends NewsBasePage {

    @ViewInject(R.id.newscenter_tpi)
    private TabPageIndicator tpi_newscenter;

    @ViewInject(R.id.newscenter_vp)
    private ViewPager vp_newscenter;
    //新闻页签的数据
    private List<NewsCenterData.NewsData.ViewTagData> viewTagDatas = new ArrayList<>();

    @OnClick(R.id.newscenter_ib_nexttag)
    public void nextTag(View view){
        //跳转到下一个页签所在的页面
        vp_newscenter.setCurrentItem(vp_newscenter.getCurrentItem()+1);//此处到最后一页时，再点击，不会越界。因为源码作了判断，当小于0时，赋值为0
    }

    public NewsNewsBasePage(HomeActivity homeActivity,List<ViewTagData> children) {
        super(homeActivity);
        viewTagDatas = children;
    }

    @Override
    public void initEvent() {
        //给viewpager添加页面监听的事件，当页面位于第一个时可滑动出左侧菜单，其余不可以。(此开源框架重写了监听方法，因此用indicator更有效)
        tpi_newscenter.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            /**
             * 监听页面停留的位置
             * @param position 当前页面所在的位置
             */
            @Override
            public void onPageSelected(int position) {
                if(position == 0){//第一个页面，可滑动左侧菜单
                    baseActivity.getSlidingMenu().setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
                }else{//其余不可以
                    baseActivity.getSlidingMenu().setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        super.initEvent();
    }

    @Override
    public View initView() {
        View newsCenterRoot = View.inflate(baseActivity,R.layout.newscenter_news_view,null);
        //通过xUtils注入组件
        ViewUtils.inject(this,newsCenterRoot);
        return newsCenterRoot;
    }

    @Override
    public void initData() {

        MyAdapter mAdapter = new MyAdapter();
        //给viewpager设置适配器
        vp_newscenter.setAdapter(mAdapter);
        //把viewpager和tabpagerindicator进行关联
        tpi_newscenter.setViewPager(vp_newscenter);
        super.initData();
    }

    /**
     * 页签对应viewpager的适配器
     */
    private class MyAdapter extends PagerAdapter{

        @Override
        public int getCount() {
            return viewTagDatas.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            NewsTpiNewspage newsTpiNewspage = new NewsTpiNewspage(baseActivity,viewTagDatas.get(position));
           View rootView =  newsTpiNewspage.getRootView();
            container.addView(rootView);
            return rootView;

            /*TextView textView = new TextView(baseActivity);
            textView.setText(viewTagDatas.get(position).title);
            textView.setGravity(Gravity.CENTER);

            vp_newscenter.addView(textView);
            return textView;*/
        }

        /**
         * 页签页面显示时调用该方法
         * @param position
         * @return
         */
        @Override
        public CharSequence getPageTitle(int position) {
            //给indicator添加标题数据
            return viewTagDatas.get(position).title;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }
}
