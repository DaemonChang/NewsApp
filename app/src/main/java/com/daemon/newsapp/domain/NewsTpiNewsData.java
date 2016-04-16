package com.daemon.newsapp.domain;

import java.util.List;

/**新闻页签的新闻数据bean
 * Created by Chang on 04/16/16.
 */
public class NewsTpiNewsData {
    public int retcode;
    public NewsTpiNewsData_Data data;

        public class NewsTpiNewsData_Data {
            public String countcommenturl;
            public String more;
            public List<NewsTpiNewsData_Data_News> news;
            public String title;
            public List<NewsTpiNewsData_Data_Topic> topic;
            public List<NewsTpiNewsData_Data_Topnews> topnews;
        }

            public class NewsTpiNewsData_Data_News{
                public String comment;
                public String commentlist;
                public String commenturl;
                public String id;
                public String listimage;
                public String pubdate;
                public String title;
                public String type;
                public String url;
             }

            public class NewsTpiNewsData_Data_Topic{
                public String description;
                public String id;
                public String listimage;
                public String sort;
                public String title;
                public String url;

            }

            public class NewsTpiNewsData_Data_Topnews{
                public String comment;
                public String commentlist;
                public String commenturl;
                public String id;
                public String pubdate;
                public String topimage;
                public String type;
                public String url;

            }

}
