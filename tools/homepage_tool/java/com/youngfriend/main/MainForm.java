package com.youngfriend.main;

import com.youngfriend.main.Main;
import com.youngfriend.utils.CommUtils;
import com.youngfriend.utils.Global;
import com.youngfriend.views.*;
import com.youngfriend.views.grouptree.GroupPnl;

import javax.swing.*;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.util.HashMap;
import java.util.List;

/**
 * Created by pandongyu on 15/1/21.
 */
public class MainForm implements BasePnl<Main> {
    private JTextField title_tf;
    private JButton btn_del_btn;
    private JButton btn_add_btn;
    private JButton tab1_add_btn;
    private JButton tab1_del_btn;
    private JButton tab2_add_btn;
    private JButton tab2_del_btn;
    private JScrollPane btn_sp;
    private JScrollPane tab1_sp;
    private JScrollPane tab2_sp;
    private JPanel root_pnl;
    private JTable btn_table;
    private JTable tab1_table;
    private JTable tab2_table;
    private NumTableCellRender numTableRender = new NumTableCellRender();

    public MainForm() {
        btn_talle_create();
        tab1_talle_create();
        tab2_talle_create();

    }

    private void tab2_talle_create() {
        tab2_table = CommUtils.createTable("类型", "标题", "高度");
        TableColumnModel cm = tab2_table.getColumnModel();
        TableColumn type_column = cm.getColumn(0);
        final JComboBox typecombo = CommUtils.createCombo(Global.tabTypeMap);
        typecombo.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent itemEvent) {
                if (ItemEvent.SELECTED == itemEvent.getStateChange() && tab2_table.isEditing()) {
                    Object item = typecombo.getSelectedItem();
                    if (item instanceof NameValDto) {
                        NameValDto dto = (NameValDto) item;
                        tab2_table.setValueAt(dto.getName(), tab2_table.getEditingRow(), 1);
                        CommUtils.stopTabelCellEditor(tab2_table);
                    }
                }
            }
        });
        type_column.setCellEditor(new DefaultCellEditor(typecombo));

        TableColumn height_column = cm.getColumn(2);
        height_column.setCellRenderer(numTableRender);
        height_column.setCellEditor(numTableRender.getTableCellEditor());
        tab2_sp.setViewportView(tab2_table);
        tab2_add_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                NameValDto item = (NameValDto) typecombo.getItemAt(0);
                CommUtils.table_AddItem(tab2_table, item, item.getName(), 340);
            }
        });
        tab2_del_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                CommUtils.table_DellItem(tab2_table);
            }
        });
    }

    private void tab1_talle_create() {
        tab1_table = CommUtils.createTable("类型", "标题logo", "高度");
        TableColumnModel cm = tab1_table.getColumnModel();
        TableColumn type_column = cm.getColumn(0);
        final JComboBox typecombo = CommUtils.createCombo(Global.tabTypeMap);
        type_column.setCellEditor(new DefaultCellEditor(typecombo));
        TableColumn height_column = cm.getColumn(2);
        TableColumn titlelogo_column = cm.getColumn(1);
        SetorTableCellRender fileSeledtRender = new SetorTableCellRender(new Do4Obj<SettorPnl>() {
            @Override
            public void doAction(SettorPnl settorPnl) {
                File file = CommUtils.getSelectFile(root_pnl, "图片", CommUtils.getImageExt());
                if (file != null) {
                    CommUtils.after_selectFile(file, settorPnl.getValue());
                    settorPnl.setValue(file.getName());
                }
            }
        });
        titlelogo_column.setCellRenderer(fileSeledtRender);
        titlelogo_column.setCellEditor(fileSeledtRender.getTableCellEditor());
        height_column.setCellRenderer(numTableRender);
        height_column.setCellEditor(numTableRender.getTableCellEditor());
        tab1_sp.setViewportView(tab1_table);
        tab1_add_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                NameValDto item = (NameValDto) typecombo.getItemAt(0);
                CommUtils.table_AddItem(tab1_table, item, "", 340);
            }
        });
        tab1_del_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                CommUtils.table_DellItem(tab1_table);
            }
        });
    }

    private void btn_talle_create() {
        btn_table = CommUtils.createTable("名称", "url", "权限");
        TableColumnModel cm = btn_table.getColumnModel();
        TableColumn privilege_column = cm.getColumn(2);

        SetorTableCellRender privilegeRender = new SetorTableCellRender(new Do4Obj<SettorPnl>() {
            @Override
            public void doAction(SettorPnl settorPnl) {
                GroupPnl editor = new GroupPnl();
                HashMap<String, Object> map = new HashMap<String, Object>();
                map.put("url", Global.weburl);
                map.put("groupid", settorPnl.getValue());
                editor.eidt(SwingUtilities.getWindowAncestor(root_pnl), map);
                if (editor.isSubmit()) {
                    settorPnl.setValue(map.get("groupid"));
                }
            }
        });
        privilege_column.setCellRenderer(privilegeRender);
        privilege_column.setCellEditor(privilegeRender.getTableCellEditor());
        btn_sp.setViewportView(btn_table);
        btn_add_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                CommUtils.table_AddItem(btn_table, "名称", "", "");
            }
        });
        btn_del_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                CommUtils.table_DellItem(btn_table);
            }
        });
    }

    @Override
    public void loadBean(Main bean) {
        CommUtils.stopTabelCellEditor(tab1_table);
        CommUtils.stopTabelCellEditor(tab2_table);
        CommUtils.stopTabelCellEditor(btn_table);
        title_tf.setText(bean.getTitle());
        CommUtils.clearTable(tab1_table);
        List<Main.Tab> tabs = bean.getLeft_tabs();
        for (Main.Tab tab : tabs) {
            CommUtils.table_AddItem(tab1_table, new NameValDto(CommUtils.getTypeName(tab.getNewType()), tab.getNewType()), tab.getTitle_logo(), tab.getHeight());
        }
        CommUtils.clearTable(tab2_table);
        tabs = bean.getRight_tabs();
        for (Main.Tab tab : tabs) {
            CommUtils.table_AddItem(tab2_table, new NameValDto(CommUtils.getTypeName(tab.getNewType()), tab.getNewType()), tab.getTitle(), tab.getHeight());
        }
        for (Main.Btn btn : bean.getBtns()) {
            CommUtils.table_AddItem(btn_table, btn.getName(), btn.getUrl(), btn.getGroupid());
        }
    }

    @Override
    public Main getBean() {
        CommUtils.stopTabelCellEditor(tab1_table);
        CommUtils.stopTabelCellEditor(tab2_table);
        CommUtils.stopTabelCellEditor(btn_table);
        Main main = new Main();
        main.setTitle(title_tf.getText());
        Window owner = SwingUtilities.getWindowAncestor(root_pnl);
        for (int i = 0; i < tab1_table.getRowCount(); i++) {
            Main.Tab tab = main.createTab();
            NameValDto newType = (NameValDto) tab1_table.getValueAt(i, 0);
            String title_logo = (String) tab1_table.getValueAt(i, 1);
            Integer height = (Integer) tab1_table.getValueAt(i, 2);
            tab.setNewType(newType.getValue());
            tab.setTitle_logo(title_logo);
            tab.setHeight(height);
            if (tab.emptyValidate()) {
                CommUtils.showMsg(owner, "不能为空");
                tab1_table.setRowSelectionInterval(i, i);
                return null;
            }
            main.addLeft_tab(tab);
        }
        for (int i = 0; i < tab2_table.getRowCount(); i++) {
            Main.Tab tab = main.createTab();
            NameValDto newType = (NameValDto) tab2_table.getValueAt(i, 0);
            String title = (String) tab2_table.getValueAt(i, 1);
            Integer height = (Integer) tab2_table.getValueAt(i, 2);
            tab.setNewType(newType.getValue());
            tab.setTitle(title);
            tab.setHeight(height);
            if (tab.emptyValidate()) {
                CommUtils.showMsg(owner, "不能为空");
                tab2_table.setRowSelectionInterval(i, i);
                return null;
            }
            main.addTab(tab);
        }

        if (btn_table.getRowCount() > 0) {
            for (int i = 0; i < btn_table.getRowCount(); i++) {
                Main.Btn btn = main.createBtn();
                btn.setName((String) btn_table.getValueAt(i, 0));
                btn.setUrl((String) btn_table.getValueAt(i, 1));
                btn.setGroupid((String) btn_table.getValueAt(i, 2));
                main.addBtn(btn);
            }
        }
        return main;
    }

    @Override
    public JPanel getRootPnl() {
        return root_pnl;
    }
}
