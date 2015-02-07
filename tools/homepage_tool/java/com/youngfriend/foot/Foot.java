package com.youngfriend.foot;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pandongyu on 14/12/30.
 */
public class Foot {
    private String image_winxin;
    private List<String> txt1 = new ArrayList<String>();
    private List<String> txt2 = new ArrayList<String>();

    public void addTxt1(String str) {
        txt1.add(str);
    }

    public void addTxt2(String str) {
        txt2.add(str);
    }

    public String getImage_winxin() {
        return image_winxin;
    }

    public void setImage_winxin(String image_winxin) {
        this.image_winxin = image_winxin;
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
}
