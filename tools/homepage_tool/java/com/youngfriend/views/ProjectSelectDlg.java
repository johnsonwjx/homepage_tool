package com.youngfriend.views;

import com.youngfriend.AppForm;
import com.youngfriend.utils.CommUtils;
import com.youngfriend.utils.Global;
import com.youngfriend.views.InputTextDialog;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.swing.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

public class ProjectSelectDlg extends JDialog {
    private final DefaultComboBoxModel project_model;
    private final AppForm appForm;
    private JPanel contentPane;
    private JButton buttonOK;
    private JTextField workspace;
    private JButton workspace_select;
    private JComboBox project;
    private JButton project_add;
    private JButton project_del;
    private final Logger logger = LogManager.getLogger(getClass());

    public ProjectSelectDlg(AppForm appForm) {
        setTitle("选择项目");
        this.appForm = appForm;
        setResizable(false);
        setContentPane(contentPane);
        setModal(true);
        pack();
        setLocationRelativeTo(AppForm.frame);
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        getRootPane().setDefaultButton(buttonOK);
        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });


// call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

// call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        //工作目录选择
        workspace_select.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JFileChooser chooser = new JFileChooser();
                chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                if (JFileChooser.APPROVE_OPTION == chooser.showOpenDialog(ProjectSelectDlg.this)) {
                    File dir = chooser.getSelectedFile();
                    if (dir.getAbsolutePath().equals(Global.workspace_dir)) {
                        //选择和以前的一样
                        return;
                    }
                    setWorkspace(dir.getAbsolutePath());
                }
            }
        });

        //工作目录增加
        project_add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    InputTextDialog dlg = new InputTextDialog(ProjectSelectDlg.this);
                    if (dlg.isOk()) {
                        File file = new File(Global.workspace_dir + File.separator + dlg.getTxt());
                        if (file.exists()) {
                            CommUtils.showMsg(ProjectSelectDlg.this, "项目已存在");
                            return;
                        }
                        file.mkdir();
                        File image_file = new File(file, "images");
                        image_file.mkdir();
                        File config_file = new File(file, "config.json");
                        config_file.createNewFile();
                        project_model.addElement(file.getName());
                        project.setSelectedIndex(project.getItemCount() - 1);
                        CommUtils.showMsg(ProjectSelectDlg.this, "创建成功");
                    }
                } catch (IOException e) {
                    logger.error(e.getMessage(), e);
                }

            }
        });
        project_model = new DefaultComboBoxModel();
        project.setModel(project_model);
        //项目删除
        project_del.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    Object item = project.getSelectedItem();
                    if (item == null) {
                        return;
                    }
                    if (!CommUtils.showConfirm(ProjectSelectDlg.this, "确认删除工程")) {
                        return;
                    }
                    File file = new File(Global.workspace_dir, (String) item);

                    FileUtils.forceDeleteOnExit(file);

                    project.setSelectedItem(null);
                    project_model.removeElement(item);
                    if (item.equals(Global.project_dir)) {
                        Global.project_dir = null;
                    }
                } catch (IOException e) {
                   logger.error(e.getMessage(),e);
                }
            }
        });

        if (!CommUtils.isStrEmpty(Global.workspace_dir) && new File(Global.workspace_dir).exists()) {
            setWorkspace(Global.workspace_dir);
        } else {
            Global.workspace_dir = null;
        }

    }

    private void onOK() {
        try {
            String item = (String) project.getSelectedItem();
            if (CommUtils.isStrEmpty(item)) {
                CommUtils.showMsg(this, "请选择项目");
                return;
            }
            Global.project_dir = item;
            File config_file = new File(Global.getProject_config_path());
            if (!config_file.exists()) {
                CommUtils.showMsg(this, "工程丢失");
                return;
            }
            appForm.load(config_file);
            CommUtils.showMsg(this, "导入成功");
            setVisible(false);
        } catch (Exception e) {
            CommUtils.showMsg(this, "导入失败");
            logger.error(e.getMessage(), e);
        }
    }

    private void onCancel() {
        appForm.closeApp();
    }

    public void setWorkspace(String str) {
        //工作目录选择
        //设置到全局
        Global.workspace_dir = str;
        workspace.setText(str);
        //删除 全局project
        Global.project_dir = null;
        project.setSelectedItem(null);
        project_model.removeAllElements();
        //重现寻找projects
        lookprojects(Global.workspace_dir);
        project_add.setEnabled(true);
        project_del.setEnabled(true);
    }


    private void lookprojects(String workspace) {
        if (workspace == null) {
            return;
        }
        File dir = new File(workspace);
        if (dir.exists()) {
            for (File file : dir.listFiles()) {
                try {
                    if (file.isDirectory() && FileUtils.directoryContains(file, new File(file, "config.json"))) {
                        project_model.addElement(file.getName());
                    }
                } catch (IOException e) {
                    logger.error(e.getMessage(), e);
                }
            }
        }
    }
}
