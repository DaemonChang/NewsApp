package com.daemon.newsapp.view;

import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.daemon.newsapp.R;
import com.daemon.newsapp.domain.NewsCenterData.NewsData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chang on 04/10/16.
 */
public class LeftMenuFragment extends BaseFragment {
    private List<NewsData> data = new ArrayList<>();//新闻中心的左侧菜单数据
    private ListView lv_leftMenuData;
    private MyAdapter mAdapter;

    @Override
    public View initView() {
       //创建ListView，用于显示左侧菜单栏的栏目名
        lv_leftMenuData = new ListView(mainActivity);
        //设置item拖动时的背景色为透明色，因为在低版本中，可全选item，会有颜色覆盖
        lv_leftMenuData.setCacheColorHint(Color.TRANSPARENT);
        //去掉item间的分割线
        lv_leftMenuData.setDividerHeight(0);
        //设置listview顶部padding为50px
        lv_leftMenuData.setPadding(0,40,0,0);
        return lv_leftMenuData;
    }

    /**由其他类来调用此方法，传递所解析到的json数据，用于左侧菜单的内容显示
     * 获取左侧菜单栏数据
     * @param data 通过GSON解释json所得到的data数据
     */
    public void setLeftMenuData(List<NewsData> data){
        this.data = data;
        mAdapter.notifyDataSetChanged();//设置好数据后，通知界面更新数据，显示
    }
    @Override
    public void initData() {
        //组织数据
        mAdapter = new MyAdapter();
        lv_leftMenuData.setAdapter(mAdapter);
        super.initData();
    }

    class MyAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            TextView tv_leftTitle;
            if(convertView == null){
                tv_leftTitle = (TextView) View.inflate(mainActivity, R.layout.leftmenutitle_listview_item,null);
            }else {
                tv_leftTitle = (TextView) convertView;

            }
            //设置左菜单栏标题数据
            tv_leftTitle.setText(data.get(position).title);
            return tv_leftTitle;
        }
    }
}
