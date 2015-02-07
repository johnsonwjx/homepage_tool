package com.youngfriend.export;

import com.youngfriend.export.TemplateExportor;
import com.youngfriend.foot.Foot;
import com.youngfriend.header.Header;
import com.youngfriend.main.Main;
import com.youngfriend.utils.Global;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.HashMap;

/**
 * Created by pandongyu on 15/2/4.
 */
public class Freemarker_TemplateExportor implements TemplateExportor {
    private final Configuration cfg;

    public Freemarker_TemplateExportor() {
// Create your Configuration instance, and specify if up to what FreeMarker
// version (here 2.3.21) do you want to apply the fixes that are not 100%
// backward-compatible. See the Configuration JavaDoc for details.
        cfg = new Configuration(Configuration.VERSION_2_3_21);
// Specify the source where the template files come from. Here I set a
// plain directory for it, but non-file-system sources are possible too:
        cfg.setClassForTemplateLoading(Freemarker_TemplateExportor.class, "/config/templates/freemarker");
        cfg.clearTemplateCache();
// Set the preferred charset template files are stored in. UTF-8 is
// a good choice in most applications:
        cfg.setDefaultEncoding("UTF-8");
        cfg.setClassicCompatible(true);
// Sets how errors will appear.
// During development TemplateExceptionHandler.HTML_DEBUG_HANDLER is better.
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.HTML_DEBUG_HANDLER);
    }

    @Override
    public void export_Main(Main main, File dir, File classes_WebInfo) throws Exception {
        Template template = cfg.getTemplate("index.tpl");
        HashMap<String, Object> root = new HashMap();
        root.put("main",main);
        root.put("image_path", "images/");
        template.process(root, new OutputStreamWriter(new FileOutputStream(new File(dir,Global.FILENAME_INDEX)),"UTF-8"));
        root.clear();
        root.put("menus", main.getBtns());
        Template menuTemplate = cfg.getTemplate("btns.tpl");
        menuTemplate.process(root, new OutputStreamWriter(new FileOutputStream(new File(classes_WebInfo,Global.FILENAME_BTN)),"UTF-8"));
    }

    @Override
    public void export_Header(Header header, File dir) throws Exception {
        Template template = cfg.getTemplate("header.tpl");
        HashMap<String, Object> root = new HashMap();
        root.put("header", header);
        root.put("image_path", "images/");
        template.process(root, new OutputStreamWriter(new FileOutputStream(new File(dir,Global.FILENAME_HEAD)),"UTF-8"));
    }

    @Override
    public void export_Foot(Foot foot, File dir) throws Exception {
        Template template = cfg.getTemplate("foot.tpl");
        HashMap<String, Object> root = new HashMap();
        root.put("foot", foot);
        root.put("image_path", "images/");
        template.process(root, new OutputStreamWriter(new FileOutputStream(new File(dir,Global.FILENAME_FOOT)),"UTF-8"));
    }

    @Override
    public void export_Demo(Main main, Header header, Foot foot) throws Exception {
        Template template = cfg.getTemplate("demo.tpl");
        HashMap<String, Object> root = new HashMap();
        root.put("foot", foot);
        root.put("header", header);
        root.put("main", main);
        root.put("image_path", Global.getProject_images_path().replaceAll("\\\\","/"));
        template.process(root, new OutputStreamWriter(new FileOutputStream(Global.demoFile),"UTF-8"));
    }
}
