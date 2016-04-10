package com.daemon.newsapp.activity;

import android.app.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.daemon.newsapp.R;
import com.daemon.newsapp.utils.DensityUtil;
import com.daemon.newsapp.utils.MyConstants;
import com.daemon.newsapp.utils.SpTools;

import java.util.ArrayList;
import java.util.List;

/**
 * 设置向导界面，采用viewpager切换页面
 */
public class GuideActivity extends Activity {

    private ViewPager vp_guide_pages;
    private LinearLayout ll_guide_points;
    private View v_guide_redpoint;
    private Button bt_guide_welcome;
    private List<ImageView> guide_pics;
    private MyAdapter mAdapter;
    private float point_gap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initView();

        initData();

        initEvent();
    }

    private void initEvent() {
        //监听全局布局完成后，触发的结果信息。用于计算红点该移动的距离。
        v_guide_redpoint.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                point_gap = ll_guide_points.getChildAt(1).getLeft() - ll_guide_points.getChildAt(0).getLeft();
                Log.d("@@potin gap :", point_gap +"");
                //因为每滑动一次界面，界面布局完成后都会触发监听，而我们只需要监听一次计算出距离即可，故取消监听。
                v_guide_redpoint.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });


        //给button设置点击监听事件
        bt_guide_welcome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //保存设置，把issetup设为true
                SpTools.setBoolean(getApplicationContext(), MyConstants.ISSETUP,true);
                //进入主界面
                Intent home = new Intent(GuideActivity.this,HomeActivity.class);
                startActivity(home);

                //关闭自己
                finish();
            }
        });

       //给viewpager设置页面改变监听事件
        vp_guide_pages.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            /**
             *
             * @param position  当前页面的页码
             * @param positionOffset  偏移比例值
             * @param positionOffsetPixels 偏移像素
             */
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                float leftMargin = point_gap * (positionOffset + position) ;
                RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) v_guide_redpoint.getLayoutParams();

                params.leftMargin = Math.round(leftMargin);//float四舍五入到int型

                v_guide_redpoint.setLayoutParams(params);
            }

            @Override
            public void onPageSelected(int position) {
                //当前viewpager显示的页码
                if(position == guide_pics.size() - 1){//如果是最后一页，则显示按钮
                    bt_guide_welcome.setVisibility(View.VISIBLE);
                }else{//其他页面均不显示
                    bt_guide_welcome.setVisibility(View.GONE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initData() {
        //初始化数据  ViewPager 需要 adapter --> list --> picture
        //图片的数据
        int[] pics = new int[]{R.drawable.guide_1,R.drawable.guide_2,R.drawable.guide_3};

        //图片容器
        guide_pics = new ArrayList<>();
        //初始化容器
        for(int i = 0; i < pics.length; i++){
            ImageView iv_temp = new ImageView(getApplicationContext());
            iv_temp.setBackgroundResource(pics[i]);
            guide_pics.add(iv_temp);//添加界面数据

            //添加灰点
            View grayPoint = new View(getApplicationContext());
            grayPoint.setBackgroundResource(R.drawable.gray_point);
            //设置灰点的大小,默认px为单位
            //通过dp与px之间的转化来适配红点的大小
            int dp = 10;
            int px = DensityUtil.dp2px(getApplicationContext(),dp);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(px,px);

            //设置间距
            if(i != 0) {
                params.leftMargin = px;
            }

            grayPoint.setLayoutParams(params);
            //添加灰点到线性布局中
            ll_guide_points.addView(grayPoint);
        }
        //创建适配器对象
        mAdapter = new MyAdapter();
        //设置适配器
        vp_guide_pages.setAdapter(mAdapter);

    }

    //Pager适配器的类
    private class MyAdapter extends PagerAdapter{

        @Override
        public int getCount() {
            return guide_pics.size();//显示页面的个数
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            //1.缓存 2.过滤:把粘在一起（同一页面）的东西显示出来。
            //object 由instantiateItem()返回。
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            //container 就是 viewpager
            //获取view
            View child = guide_pics.get(position);
            //添加view
            container.addView(child);
            return child;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            //从viewpager中移除
            container.removeView((View) object);
        }
    }

    private void initView() {
        setContentView(R.layout.activity_guide);
        //ViewPager组件
        vp_guide_pages = (ViewPager) findViewById(R.id.vp_guide_pages);
        //向导 点 的容器
        ll_guide_points = (LinearLayout) findViewById(R.id.ll_guide_points);

        //红点
        v_guide_redpoint = findViewById(R.id.v_guide_redpoint);

        //开始体验 按钮
        bt_guide_welcome = (Button) findViewById(R.id.bt_guide_welcome);
    }
}
