package com.youngfriend.header;

import com.youngfriend.utils.CommUtils;
import com.youngfriend.utils.Global;
import com.youngfriend.views.BasePnl;
import com.youngfriend.views.NameValDto;

import javax.swing.*;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;

/**
 * Created by pandongyu on 15/1/21.
 */
public class HeaderForm implements BasePnl<Header>{
    private JTextField logo_tf;
    private JButton logo_btn;
    private JScrollPane txt1_sp;
    private JScrollPane txt2_sp;
    private JScrollPane nav_sp;
    private JButton txt1_del_btn;
    private JButton txt1_add_btn;
    private JButton nav_del_btn;
    private JButton nav_add_btn;
    private JPanel root_pnl;
    private JButton txt2_add_btn;
    private JButton txt2_del_btn;
    private JTextField flash_tf;
    private JButton flash_path_btn;
    private JTable txt1_table;
    private JTable txt2_table;
    private JTable nav_table;

    public HeaderForm() {
        txt1_talle_create();
        txt2_talle_create();
        nav_talle_create();
        logo_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                File file=CommUtils.getSelectFile(root_pnl,"图片",CommUtils.getImageExt()) ;
                if(file!=null){
                    CommUtils.after_selectFile(file,logo_tf.getText());
                    logo_tf.setText(file.getName());
                }
            }
        });
        flash_path_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                File file=CommUtils.getSelectFile(root_pnl,"flash","swf") ;
                if(file!=null){
                    CommUtils.after_selectFile(file,flash_tf.getText());
                    flash_tf.setText(file.getName());
                }
            }
        });
    }

    private void nav_talle_create() {
        nav_table=CommUtils.createTable("文字","url");
        TableColumnModel cm = nav_table.getColumnModel();
        TableColumn title_column = cm.getColumn(0);
        final JComboBox combo = CommUtils.createCombo(Global.navMap);
        combo.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent itemEvent) {
                if(ItemEvent.SELECTED==itemEvent.getStateChange()&&nav_table.isEditing()){
                    Object obj=combo.getSelectedItem() ;
                    if(obj instanceof NameValDto){
                        NameValDto item= (NameValDto) obj;
                        nav_table.setValueAt(item.getValue(),nav_table.getEditingRow(),1);
                        CommUtils.stopTabelCellEditor(nav_table);
                    }
                }
            }
        });
        title_column.setCellEditor(new DefaultCellEditor(combo));

        nav_sp.setViewportView(nav_table);
        nav_add_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                NameValDto dto= (NameValDto) combo.getItemAt(0);
                CommUtils.table_AddItem(nav_table,dto,dto.getValue());
            }
        });
        nav_del_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                CommUtils.table_DellItem(nav_table);
            }
        });
    }

    private void txt2_talle_create() {
        txt2_table = CommUtils.createTable("文字");
        txt2_sp.setViewportView(txt2_table);
        txt2_add_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                CommUtils.table_AddItem(txt2_table, "文字");
            }
        });
        txt2_del_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                CommUtils.table_DellItem(txt2_table);
            }
        });
    }

    private void txt1_talle_create() {
        txt1_table = CommUtils.createTable("文字");
        txt1_sp.setViewportView(txt1_table);
        txt1_add_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                CommUtils.table_AddItem(txt1_table, "文字");
            }
        });
        txt1_del_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                CommUtils.table_DellItem(txt1_table);
            }
        });
    }


    @Override
    public void loadBean(Header bean) {
        CommUtils.stopTabelCellEditor(txt1_table);
        CommUtils.stopTabelCellEditor(txt2_table);
        CommUtils.stopTabelCellEditor(nav_table);
        txt1_table.clearSelection();
        CommUtils.clearTable(txt1_table);
        txt2_table.clearSelection();
        CommUtils.clearTable(txt2_table);
        nav_table.clearSelection();
        CommUtils.clearTable(nav_table);
        logo_tf.setText(bean.getLogopath());
        flash_tf.setText(bean.getFlashpath());
        java.util.List<String> txts = bean.getTxt1();
        for (String txt : txts) {
            CommUtils.table_AddItem(txt1_table, txt);
        }
        txts = bean.getTxt2();
        for (String txt : txts) {
            CommUtils.table_AddItem(txt2_table, txt);
        }
        java.util.List<Header.Nav> navs = bean.getNavs();
        for (Header.Nav nav : navs) {
            CommUtils.table_AddItem(nav_table, nav.getTitle(), nav.getUrl());
        }
    }

    @Override
    public Header getBean() {
        CommUtils.stopTabelCellEditor(txt1_table);
        CommUtils.stopTabelCellEditor(txt2_table);
        CommUtils.stopTabelCellEditor(nav_table);
        Header header = new Header();
        Window owner=SwingUtilities.getWindowAncestor(root_pnl) ;

        header.setLogopath(logo_tf.getText());
        for (int i = 0; i < txt1_table.getRowCount(); i++) {
            String value = (String) txt1_table.getValueAt(i, 0);
            if (CommUtils.isStrEmpty(value)) {
                CommUtils.showMsg(owner, "不能为空");
                txt1_table.setRowSelectionInterval(i, i);
                return null;
            }
            header.addTxt1(value);
        }
        for (int i = 0; i < txt2_table.getRowCount(); i++) {
            String value = (String) txt2_table.getValueAt(i, 0);
            if (CommUtils.isStrEmpty(value)) {
                CommUtils.showMsg(owner, "不能为空");
                txt2_table.setRowSelectionInterval(i, i);
                return null;
            }
            header.addTxt2(value);
        }
        for (int i = 0; i < nav_table.getRowCount(); i++) {
            Object titleObj = nav_table.getValueAt(i, 0);
            String title = null;
            if (titleObj instanceof NameValDto) {
                title = ((NameValDto) titleObj).getName();
            } else {
                title = (String) titleObj;
            }
            String url = (String) nav_table.getValueAt(i, 1);
            if (CommUtils.isStrEmpty(title) || CommUtils.isStrEmpty(url)) {
                CommUtils.showMsg(owner, "不能为空");
                nav_table.setRowSelectionInterval(i, i);
                return null;
            }
            Header.Nav nav = header.createNav();
            nav.setTitle(title);
            nav.setUrl(url);
            header.addNav(nav);
            header.setFlashpath(flash_tf.getText());
        }
        return header;
    }

    @Override
    public JPanel getRootPnl() {
        return root_pnl;
    }
}
