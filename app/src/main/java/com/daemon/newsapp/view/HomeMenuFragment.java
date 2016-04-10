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


    @ViewInject(R.id.vp_home_pages)
    private ViewPager viewPager;

    @ViewInject(R.id.rg_content_buttons)
    private RadioGroup radioGroup;

    @Override
    public View initView() {
        View root = View.inflate(currentActivity, R.layout.fragment_home_view,null);

        //xUtils 动态注入
        ViewUtils.inject(this,root);
        return root;
    }

    @Override
    public void initData() {
        //添加5个pager
        pagers.add(new HomeBaseTagPager(currentActivity));
        pagers.add(new NewsBaseTagPager(currentActivity));
        pagers.add(new ServiceBaseTagPager(currentActivity));
        pagers.add(new GovAffairsBaseTagPager(currentActivity));
        pagers.add(new SettingBaseTagPager(currentActivity));

        MyAdapter mAdapter = new MyAdapter();

        viewPager.setAdapter(mAdapter);

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
