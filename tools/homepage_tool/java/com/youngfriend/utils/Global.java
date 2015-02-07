package com.youngfriend.utils;


import java.io.File;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by pandongyu on 14/12/30.
 */
public class Global {
    public static final String FILENAME_HEAD = "header.html";
    public static final String FILENAME_FOOT = "foot.html";
    public static final String FILENAME_INDEX = "index.jsp";
    public static final String FILENAME_BTN = "menu.xml";
    public static String workspace_dir=null;
    public static String project_dir =null;
    public static String config_dir=Global.class.getClassLoader().getResource("config").getFile();
    public static String weburl;
    public static File demoFile= new File(Global.getSrc_webDir(), "demo.html");

    public static String getProject_path(){
        return workspace_dir+ File.separator+project_dir ;
    }

    public static String getProject_images_path(){
        return getProject_path()+File.separator+"images" ;
    }
    public static String getProject_config_path(){
        return getProject_path()+File.separator+"config.json" ;
    }


    public static String getSrc_webDir(){
        return config_dir+File.separator+"webfiles";
    }

    public static final Map<String, String> tabTypeMap;static{
        tabTypeMap=new LinkedHashMap<String, String>();
        tabTypeMap.put("滚动信息", "00");
        tabTypeMap.put("滚动图片", "05");
        tabTypeMap.put("公司新闻", "10");
        tabTypeMap.put("业界资讯", "20");
        tabTypeMap.put("公司公告", "30");
        tabTypeMap.put("党团工会", "40");
        tabTypeMap.put("问卷调查", "50");
        tabTypeMap.put("期刊管理", "60");
        tabTypeMap.put("应用附件", "90");
    }

    public static final Map<String,String> navMap;static{
        navMap=new LinkedHashMap<String, String>();
        navMap.put("首页", "index.jsp");
        navMap.put("公司新闻", "news.jsp?newsType=10");
        navMap.put("业界资讯", "news.jsp?newsType=20");
        navMap.put("公司公告", "news.jsp?newsType=30");
        navMap.put("党团工会", "news.jsp?newsType=40");
        navMap.put("问卷调查", "news.jsp?newsType=50");
        navMap.put("期刊管理", "news.jsp?newsType=60");
    }

}
