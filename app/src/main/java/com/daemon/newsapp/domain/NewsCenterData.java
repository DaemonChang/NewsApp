package com.daemon.newsapp.domain;

import java.util.List;

/**新闻中心json数据的bean
 * 按结构 构建
 * Created by Chang on 04/12/16.
 */
public class NewsCenterData {
    public int retcode;
    public List<NewsData> data;

     public class NewsData{
            public List<ViewTagData> children ;
             /**
              * 新闻中心上方页签的数据
              */
            public class ViewTagData{
                 public String id;
                 public String title;
                 public int type;
                 public String url	;
            }
         public String id;
         public String title;
         public  int type;
         public String url	;
         public String url1	;

         public String dayurl;
         public String  excurl;
         public String  weekurl;


    }
    public List<String> extend;

}
