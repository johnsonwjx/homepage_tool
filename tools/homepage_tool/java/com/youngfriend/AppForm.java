package com.youngfriend;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.youngfriend.beans.JsonObj;
import com.youngfriend.export.Freemarker_TemplateExportor;
import com.youngfriend.export.TemplateExportor;
import com.youngfriend.foot.Foot;
import com.youngfriend.foot.FootForm;
import com.youngfriend.header.Header;
import com.youngfriend.header.HeaderForm;
import com.youngfriend.main.Main;
import com.youngfriend.main.MainForm;
import com.youngfriend.utils.CommUtils;
import com.youngfriend.utils.Global;
import com.youngfriend.utils.MyValidator;
import com.youngfriend.views.BusyDialog;
import com.youngfriend.views.InputTextDialog;
import com.youngfriend.views.ProjectSelectDlg;
import com.youngfriend.views.ShowDialog;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Properties;

public class AppForm {
    public static JFrame frame;

    private JPanel rootPanel;
    private JTabbedPane tab_pnl;
    private JButton save_btn;
    private JTextField web_com;
    private JButton create_btn;
    private JButton project_btn;
    private JLabel project_txt;
    private JButton preview;
    private JButton clear;
    private Properties props = null;
    private HeaderForm headerForm = null;
    private FootForm footForm = null;
    private MainForm mainPnlForm = null;
    private static final Logger logger = LogManager.getLogger(AppForm.class);
    private ProjectSelectDlg projectSelectDlg = null;
    private ObjectMapper mapper = new ObjectMapper();
    private String old_json = null;
    public static BusyDialog busyDialog = null;
    private TemplateExportor templateExportor = null;

    public AppForm() {
        initMainTap();
        loadConfig();
        projectSelectDlg = new ProjectSelectDlg(this);
        checkProjectDir();
        initOper();
        templateExportor = new Freemarker_TemplateExportor();
    }

    private void initMainTap() {
        headerForm = new HeaderForm();
        footForm = new FootForm();
        mainPnlForm = new MainForm();
        tab_pnl.addTab("头页面", headerForm.getRootPnl());
        tab_pnl.addTab("尾页面", footForm.getRootPnl());
        tab_pnl.addTab("主页面", mainPnlForm.getRootPnl());
        web_com.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent focusEvent) {
                Global.weburl = web_com.getText();
            }
        });
    }

    private void initOper() {
        save_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                save_project();
            }

        });
        project_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (checkChange() && CommUtils.showConfirm(frame, "工程有改变，是否保存 ")) {
                    save_project();
                }
                project_select();
            }
        });
        preview.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                preview();
            }

        });
        create_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                create_Html();
            }
        });
        clear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (CommUtils.showConfirm(frame, "确定清空")) {
                    try {
                        clear();
                    } catch (Exception e) {
                        logger.error(e.getMessage(), e);
                    }
                }
            }
        });

    }

    private void preview() {
        try {
            SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
                @Override
                protected Void doInBackground() throws Exception {
                    JsonObj obj = getJsonObj();
                    templateExportor.export_Demo(obj.getMain(), obj.getHeader(), obj.getFoot());
                    return null;
                }

                @Override
                protected void done() {
                    busyDialog.setVisible(false);
                    new ShowDialog(frame, "预览地址", "file://" + Global.demoFile.getAbsolutePath());
                }
            };
            worker.execute();
            busyDialog.setVisible(true);
        } catch (Exception e) {
            CommUtils.showMsg(frame, "生成失败");
            logger.error(e.getMessage(), e);
        } finally {
            if (busyDialog.isVisible()) {
                busyDialog.setVisible(false);
            }
        }
    }


    private void checkProjectDir() {
        project_select();
        if (CommUtils.isStrEmpty(Global.project_dir)) {
            closeApp();
        }
    }

    private JsonObj getJsonObj() {
        JsonObj obj = new JsonObj();
        Header header = headerForm.getBean();
        if (header == null) {
            tab_pnl.setSelectedIndex(0);
            return null;
        }
        Main main = mainPnlForm.getBean();
        if (main == null) {
            return null;
        }
        Foot foot = footForm.getBean();
        if (foot == null) {
            return null;
        }
        obj.setMain(main);
        obj.setHeader(header);
        obj.setFoot(foot);
        obj.setWeburl(Global.weburl);
        return obj;
    }

    private boolean checkChange() {
        try {
            JsonObj obj = getJsonObj();
            return !mapper.writeValueAsString(obj).equals(old_json);
        } catch (JsonProcessingException e) {
            logger.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    private void project_select() {
        projectSelectDlg.setVisible(true);
        project_txt.setText(Global.project_dir);

    }

    private void save_project() {
        JsonObj obj = getJsonObj();
        try {
            mapper.writeValue(new File(Global.getProject_config_path()), obj);
            old_json = mapper.writeValueAsString(obj);
            CommUtils.showMsg(frame, "保存成功");
        } catch (FileNotFoundException e) {
            logger.error(e.getMessage(), e);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
    }

    public void clear() throws Exception {
        load(new JsonObj());
    }

    public void load(File config_file) throws Exception {
        if (FileUtils.sizeOf(config_file) <= 0) {
            clear();
            return;
        }
        JsonObj obj = mapper.readValue(config_file, JsonObj.class);
        load(obj);
        old_json = mapper.writeValueAsString(getJsonObj());
    }

    public void load(JsonObj obj) throws Exception {
        Header header = obj.getHeader();
        headerForm.loadBean(header);
        Main main = obj.getMain();
        mainPnlForm.loadBean(main);
        Foot foot = obj.getFoot();
        footForm.loadBean(foot);
        web_com.setText(obj.getWeburl());
        Global.weburl = web_com.getText();
    }


    private void create_Html() {
        InputTextDialog inputTxt = new InputTextDialog(frame, "请输入生成项目名称", new MyValidator<String>() {
            @Override
            public boolean validato(Window owner, String str) {
                if (!str.matches("^[a-zA-Z_]+$")) {
                    CommUtils.showMsg(owner, "名称必须是字母和下划线");
                    return false;
                }
                return true;
            }
        });
        if(!inputTxt.isOk()){
            return;
        }
        final String projectName = inputTxt.getTxt();
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("请选V6Web根目录");
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        if (chooser.showSaveDialog(frame) != JFileChooser.APPROVE_OPTION) {
            return;
        }
        File webdir = chooser.getSelectedFile();
        if (webdir == null) {
            return;
        }
        final File classes_WebInfo = FileUtils.getFile(webdir, "WEB-INF/classes");
        if (classes_WebInfo == null||!classes_WebInfo.exists()) {
            CommUtils.showMsg(frame, "web跟目录不正确");
            return;
        }
        try {
            final File projectDir = new File(webdir, projectName);
            if (projectDir.exists()) {
                if (!CommUtils.showConfirm(frame, "项目目录已存在,是否清空原目录,覆盖?")) {
                    return;
                }
                FileUtils.cleanDirectory(projectDir);
            }else{
                projectDir.mkdir();
            }
            SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
                @Override
                protected Void doInBackground() throws Exception {
                    JsonObj obj = getJsonObj();
                    //pages copy
                    File pages_src = new File(Global.getSrc_webDir(), "pages");
                    FileUtils.copyDirectory(pages_src, projectDir);

                    templateExportor.export_Header(obj.getHeader(), projectDir);
                    templateExportor.export_Foot(obj.getFoot(), projectDir);
                    templateExportor.export_Main(obj.getMain(), projectDir,classes_WebInfo);
                    File image_dir = new File(projectDir, "images");
                    FileUtils.copyDirectoryToDirectory(new File(Global.getSrc_webDir(), "images"), projectDir);
                    File project_image_dir = new File(Global.getProject_images_path());
                    for (File file : project_image_dir.listFiles()) {
                        FileUtils.copyFileToDirectory(file, image_dir);
                    }
                    File css_dir = new File(projectDir, "css");
                    css_dir.mkdir();
                    FileUtils.copyFileToDirectory(new File(Global.getSrc_webDir() + File.separator + "css", "csshover.htc"), css_dir);
                    FileUtils.copyFileToDirectory(new File(Global.getSrc_webDir() + File.separator + "css", "style.css"), css_dir);
                    FileUtils.copyDirectoryToDirectory(new File(Global.getSrc_webDir(), "js"), projectDir);
                    return null;
                }

                @Override
                protected void done() {
                    try {
                        this.get();
                        busyDialog.setVisible(false);
                        CommUtils.showMsg(frame, "生成html成功");
                    } catch (Exception e) {
                        logger.error(e.getMessage(), e);
                        CommUtils.showMsg(frame, "生成失败");
                    }
                }
            };
            worker.execute();
            busyDialog.setVisible(true);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            CommUtils.showMsg(frame, "生成失败");
        } finally {
            if (busyDialog.isVisible()) {
                busyDialog.setVisible(false);
            }
        }

    }


    public void closeApp() {
        if (CommUtils.showConfirm(frame, "确定退出")) {
            FileOutputStream out = null;
            try {
                out = new FileOutputStream(new File(Global.config_dir, "config.properties"));
                if (Global.workspace_dir != null) {
                    props.setProperty("workspace", Global.workspace_dir);
                    props.store(out, "config");
                }
            } catch (IOException e) {
                logger.error(e.getMessage(), e);
            } finally {
                try {
                    out.close();
                } catch (IOException e) {
                    logger.error(e.getMessage(), e);
                }
            }
            if (frame != null) {
                frame.dispose();
            }
            System.exit(0);
        }
    }

    private void loadConfig() {
        props = new Properties();
        try {
            props.load(new FileInputStream(new File(Global.config_dir, "config.properties")));
            if (!CommUtils.isStrEmpty(props.getProperty("workspace"))) {
                File file = new File(props.getProperty("workspace"));
                if (file.exists()) {
                    Global.workspace_dir = file.getAbsolutePath();
                }
            }
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                final AppForm mainform = new AppForm();
                frame = new JFrame("门户设计器");
                frame.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent windowEvent) {
                        mainform.closeApp();
                    }
                });
                frame.setContentPane(mainform.rootPanel);
                frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                frame.pack();
                frame.setLocationByPlatform(true);
                frame.setLocationRelativeTo(null);
                busyDialog = new BusyDialog(frame);
                frame.setVisible(true);
            }
        });
    }


}
