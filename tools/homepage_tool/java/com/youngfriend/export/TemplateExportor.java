package com.youngfriend.export;

import com.youngfriend.foot.Foot;
import com.youngfriend.header.Header;
import com.youngfriend.main.Main;

import java.io.File;

/**
 * Created by pandongyu on 15/2/4.
 */
public interface TemplateExportor {
    void export_Main(Main main, File dir, File classes_WebInfo) throws Exception;
    void export_Header(Header header,File dir) throws  Exception;
    void export_Foot(Foot foot,File dir) throws  Exception;
    void export_Demo(Main main,Header header ,Foot foot) throws  Exception;


}
