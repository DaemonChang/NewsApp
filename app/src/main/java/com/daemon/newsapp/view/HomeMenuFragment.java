package com.daemon.newsapp.view;



import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;


import com.daemon.newsapp.R;
import com.daemon.newsapp.basepager.BaseTagPager;
import com.daemon.newsapp.basepager.GovAffairsBaseTagPager;
import com.daemon.newsapp.basepager.HomeBaseTagPager;
import com.daemon.newsapp.basepager.NewsBaseTagPager;
import com.daemon.newsapp.basepager.ServiceBaseTagPager;
import com.daemon.newsapp.basepager.SettingBaseTagPager;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Chang on 04/10/16.
 */
public class HomeMenuFragment extends BaseFragment {
    //装载5个pager的容器
    private List<BaseTagPager> pagers = new ArrayList<>();
    private int selectIndex = -1; //记录选择的按钮所对应的页面下标

    @ViewInject(R.id.vp_home_pages)
    private ViewPager viewPager;

    @ViewInject(R.id.rg_content_buttons)
    private RadioGroup radioGroup;



    @Override
    public void initEvent() {

        //定义底部导航按钮的事件

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                switch (checkedId){
                    case R.id.rb_content_head:
                        selectIndex = 0;
                        break;
                    case R.id.rb_content_news:
                        selectIndex = 1;
                        break;
                    case R.id.rb_content_service:
                        selectIndex = 2;
                        break;
                    case R.id.rb_content_govaffair:
                        selectIndex = 3;
                        break;
                    case R.id.rb_content_setting:
                        selectIndex = 4;
                        break;

                }//switch

                switchPage();
            }
        });

        super.initEvent();
    }

    /**
     * 左侧菜单点击时，让主界面切换到对应的页面
     */
    public void leftMenuClickSwitchPage(int subSelectIndex){
        //获取到选中的主菜单页面
        BaseTagPager baseTagPager = pagers.get(selectIndex);
        //在该主菜单页面上，显示对应的左侧菜单栏所指定的页面
        baseTagPager.switchNewsPage(subSelectIndex);

    }

    /**
     * 显示选用的按钮所对应的页面
     */
    private void switchPage() {
        //设置viewpager当前显示的页面
        viewPager.setCurrentItem(selectIndex);

        //如果是 首页 或者 设置 页面，不让侧滑栏 划出
        if(selectIndex == 0 || selectIndex == pagers.size() - 1){
            mainActivity.getSlidingMenu().setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
        }else{
            //其余可以滑出
            mainActivity.getSlidingMenu().setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        }
    }

    @Override
    public View initView() {
        View root = View.inflate(mainActivity, R.layout.fragment_home_view,null);

        //xUtils 动态注入
        ViewUtils.inject(this,root);
        return root;
    }

    @Override
    public void initData() {
        //添加5个pager
        pagers.add(new HomeBaseTagPager(mainActivity));
        pagers.add(new NewsBaseTagPager(mainActivity));
        pagers.add(new ServiceBaseTagPager(mainActivity));
        pagers.add(new GovAffairsBaseTagPager(mainActivity));
        pagers.add(new SettingBaseTagPager(mainActivity));

        MyAdapter mAdapter = new MyAdapter();

        viewPager.setAdapter(mAdapter);

        //设置初始登录后 默认 首页按钮被选中
        radioGroup.check(R.id.rb_content_head);
    }

    private class MyAdapter extends PagerAdapter{

        @Override
        public int getCount() {
            return pagers.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            BaseTagPager baseTagPager = pagers.get(position);
            View root = baseTagPager.getRoot();
            container.addView(root);
            return root;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }
}
