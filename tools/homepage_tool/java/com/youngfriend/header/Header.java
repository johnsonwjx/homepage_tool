package com.youngfriend.header;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pandongyu on 14/12/30.
 */
public class Header {
    private String logopath;

    public String getFlashpath() {
        return flashpath;
    }

    public void setFlashpath(String flashpath) {
        this.flashpath = flashpath;
    }

    private String flashpath;
    private List<String> txt1 = new ArrayList<String>();
    private List<String> txt2 = new ArrayList<String>();
    private List<Nav> navs = new ArrayList<Nav>();

    public void addTxt1(String str) {
        txt1.add(str);
    }

    public void addTxt2(String str) {
        txt2.add(str);
    }

    public void addNav(Nav nav) {
        navs.add(nav);
    }

    public String getLogopath() {
        return logopath;
    }

    public void setLogopath(String logopath) {
        this.logopath = logopath;
    }

    public List<String> getTxt1() {
        return txt1;
    }

    public void setTxt1(List<String> txt1) {
        this.txt1 = txt1;
    }

    public List<String> getTxt2() {
        return txt2;
    }

    public void setTxt2(List<String> txt2) {
        this.txt2 = txt2;
    }

    public List<Nav> getNavs() {
        return navs;
    }

    public void setNavs(List<Nav> navs) {
        this.navs = navs;
    }

    public Nav createNav() {
        return new Nav();
    }

    public static class Nav {
        private String title;
        private String url;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
