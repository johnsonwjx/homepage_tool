package com.youngfriend.beans;

import com.youngfriend.foot.Foot;
import com.youngfriend.header.Header;
import com.youngfriend.main.Main;

/**
 * Created by pandongyu on 15/1/13.
 */
public class JsonObj {
    private Header header;
    private Main main;
    private Foot foot;

    public String weburl = "";

    public String getWeburl() {
        return weburl;
    }

    public void setWeburl(String weburl) {
        this.weburl = weburl;
    }

    public JsonObj() {
        header = new Header();
        main = new Main();
        foot = new Foot();
    }

    public Header getHeader() {
        return header;
    }

    public void setHeader(Header header) {
        this.header = header;
    }


    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public Foot getFoot() {
        return foot;
    }

    public void setFoot(Foot foot) {
        this.foot = foot;
    }

}
