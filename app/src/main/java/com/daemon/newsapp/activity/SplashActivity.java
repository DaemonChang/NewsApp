package com.daemon.newsapp.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

import com.daemon.newsapp.R;
import com.daemon.newsapp.utils.MyConstants;
import com.daemon.newsapp.utils.SpTools;

public class SplashActivity extends Activity {

    private ImageView iv_splash_main_view;
    private AnimationSet as;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initView();//初始化界面

        startAnimation();//播放动画

        initEvent();//初始化事件
    }

    /**
     * 初始化事件
     */
    private void initEvent() {
        //1.监听动画播放完的事件（只有一处用到，申明为匿名对象；多处的话，则申明为成员变量）
        as.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
            //监听动画结束
            // 2.判断进入 向导界面 还是 主界面
                if(SpTools.getBoolean(getApplicationContext(),MyConstants.ISSETUP,false)){
                    //true ,进入主界面
                   // Log.d("@@@","main View");
                    Intent home = new Intent(SplashActivity.this,HomeActivity.class);
                    startActivity(home);

                }else {
                    //false,进去向导界面
                   // Log.d("@@@","guide View");
                    Intent intent = new Intent(SplashActivity.this,GuideActivity.class);
                    startActivity(intent);

                }
                finish();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

    }

    /**
     *动画集：旋转 + 缩放 + 渐变
     */
    private void startAnimation() {
        //false:表示 动画集中各每种动画都采用各自的动画插入器（参数）
        as = new AnimationSet(false);

        //旋转
        RotateAnimation ra  = new RotateAnimation(
                0,360,
                Animation.RELATIVE_TO_SELF,0.5f,
                Animation.RELATIVE_TO_SELF,0.5f
        );
        ra.setDuration(3000);
        //添加旋转动画
        as.addAnimation(ra);

        //缩放
        ScaleAnimation sa = new ScaleAnimation(
                0,1,
                0,1,
                Animation.RELATIVE_TO_SELF,0.5f,
                Animation.RELATIVE_TO_SELF,0.5f
        );
        sa.setDuration(3000);
        //添加缩放动画
        as.addAnimation(sa);

        //渐变
        AlphaAnimation aa = new AlphaAnimation(0,1);
        aa.setDuration(3000);
        //添加渐变动画
        as.addAnimation(aa);

        //给主splash开始动画集
        iv_splash_main_view.startAnimation(as);

        //动画播放完后，进入 向导页面 或 主页面

        //1.设置监听 动画播放完的 事件

        //2.判断进入 向导界面 还是 主界面

    }

    private void initView() {
        //设置主界面
        setContentView(R.layout.activity_splash);

        //初始化控件
        iv_splash_main_view = (ImageView) findViewById(R.id.iv_splash_main_view);

    }
}
