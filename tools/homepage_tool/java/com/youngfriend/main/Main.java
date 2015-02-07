package com.youngfriend.main;


import com.youngfriend.utils.CommUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pandongyu on 14/12/30.
 */
public class Main {
    private String title="";

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Tab> getRight_tabs() {
        return right_tabs;
    }
    private List<Btn> btns=new ArrayList<Btn>();

    public List<Btn> getBtns() {
        return btns;
    }

    public void setBtns(List<Btn> btns) {
        this.btns = btns;
    }
    public void setRight_tabs(List<Tab> right_tabs) {
        this.right_tabs = right_tabs;
    }

    private List<Tab> right_tabs = new ArrayList<Tab>();
    private List<Tab> left_tabs = new ArrayList<Tab>();

    public void addLeft_tab(Tab tab) {
        left_tabs.add(tab);
    }

    public List<Tab> getLeft_tabs() {
        return left_tabs;
    }

    public void setLeft_tabs(List<Tab> left_tabs) {
        this.left_tabs = left_tabs;
    }

    public Tab createTab() {
        return new Tab();
    }


    public void addTab(Tab tab) {
        right_tabs.add(tab);
    }

    public Btn createBtn(){
        return new Btn() ;
    }
    public void addBtn(Btn btn){
        btns.add(btn);
    }

    public static class Tab {
        private String title;
        private String newType;
        private String title_logo;


        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }

        private int height;

        public String getTitle_logo() {
            return title_logo;
        }

        public void setTitle_logo(String title_logo) {
            this.title_logo = title_logo;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getNewType() {
            return newType;
        }

        public void setNewType(String newType) {
            this.newType = newType;
        }

        public boolean emptyValidate() {
            if (CommUtils.isStrEmpty(newType) || height <= 0) {
                return true;
            }
            return false;
        }
    }

    public static class Btn {
        private String name;
        private String url;
        private String groupid;

        public String getGroupid() {
            return groupid;
        }

        public void setGroupid(String groupid) {
            this.groupid = groupid;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
