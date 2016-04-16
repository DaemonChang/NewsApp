package com.daemon.newsapp.view;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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
//--------------定义一个监听接口，给别的类实现该接口来传递主页面选中的页面------
   /* public interface OnSwitchPageListener{
        void switchPage(int selectIndex);
    }

    private OnSwitchPageListener switchPageListener;

    public void setOnSwitchPageListener(OnSwitchPageListener listener){
        this.switchPageListener = listener;
    }*/
//------------此功能可代替 mainActivity.getHomeMenuFragment().leftMenuClickSwitchPage(selectPosition);----


    private List<NewsData> data = new ArrayList<>();//新闻中心的左侧菜单数据
    private ListView lv_leftMenuData;
    private MyAdapter mAdapter;

    private int selectPosition;//选中的位置



    @Override
    public void initEvent() {
       lv_leftMenuData.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               //保存选中的位置信息
               selectPosition = position;
               //刷新页面
               mAdapter.notifyDataSetChanged();
               //方法1：控制主菜单选中的页面的 左侧栏中的 所选中的标签 所对应的页面的显示
                mainActivity.getHomeMenuFragment().leftMenuClickSwitchPage(selectPosition);
                //方法2：
           /*    if(switchPageListener != null) {
                   Log.d("@@swtichpage:" , "swtichPageListener.switchPage()");
                   switchPageListener.switchPage(selectPosition);
               }else{
                   Log.d("@@swtichpage:" , "mainActivity.getHomeMenuFragment()");
                   mainActivity.getHomeMenuFragment().leftMenuClickSwitchPage(selectPosition);
               }*/

               //左菜单栏收回
               mainActivity.getSlidingMenu().toggle();
           }
       });


        super.initEvent();
    }

    @Override
    public View initView() {
       //创建ListView，用于显示左侧菜单栏的栏目名
        lv_leftMenuData = new ListView(mainActivity);
        //设置选中时的颜色才透明色
        lv_leftMenuData.setSelector(new ColorDrawable(Color.TRANSPARENT));

        //设置item拖动时的背景色为透明色，因为在低版本中，可全选item，会有颜色覆盖
        lv_leftMenuData.setCacheColorHint(Color.TRANSPARENT);
        //去掉item间的分割线
        lv_leftMenuData.setDividerHeight(0);
        //设置listview顶部padding为50px
        lv_leftMenuData.setPadding(0,40,0,0);
        Log.i("lv",lv_leftMenuData.toString());
        return lv_leftMenuData;
    }


    @Override
    public void initData() {
        super.initData();

        //组织数据
        mAdapter = new MyAdapter();
        Log.d("@@mAdapter::",mAdapter+"");
        lv_leftMenuData.setAdapter(mAdapter);
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

            //设置选中与否 的状态
            tv_leftTitle.setEnabled(position == selectPosition);
            return tv_leftTitle;
        }
    }
    /**由其他类来调用此方法，传递所解析到的json数据，用于左侧菜单的内容显示
     * 获取左侧菜单栏数据
     * @param data 通过GSON解释json所得到的data数据
     */
    public void setLeftMenuData(List<NewsData> data){
        this.data = data;
        mAdapter.notifyDataSetChanged();//设置好数据后，通知界面更新数据，显示
    }
}
