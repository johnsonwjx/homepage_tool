package com.youngfriend.foot;

import com.youngfriend.foot.Foot;
import com.youngfriend.utils.CommUtils;
import com.youngfriend.views.BasePnl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * Created by pandongyu on 15/1/21.
 */
public class FootForm implements BasePnl<Foot>{
    private JTextField weixin_tf;
    private JButton weixin_btn;
    private JScrollPane txt1_sp;
    private JScrollPane txt2_sp;
    private JButton txt1_del_btn;
    private JButton txt1_add_btn;
    private JButton txt2_add_btn;
    private JButton txt2_del_btn;
    private JPanel root_pnl;
    private JTable txt1_table;
    private JTable txt2_table;

    public FootForm() {
        txt1_talle_create();
        txt2_talle_create();
        weixin_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                File file= CommUtils.getSelectFile(root_pnl, "图片", CommUtils.getImageExt()) ;
                if(file!=null){
                    CommUtils.after_selectFile(file,weixin_tf.getText());
                    weixin_tf.setText(file.getName());
                }
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
    public void loadBean(Foot bean) {
        CommUtils.stopTabelCellEditor(txt1_table);
        CommUtils.stopTabelCellEditor(txt2_table);
        weixin_tf.setText(bean.getImage_winxin());
        java.util.List<String> txts = bean.getTxt1();
        CommUtils.clearTable(txt1_table);
        for (String txt : txts) {
            CommUtils.table_AddItem(txt1_table, new Object[]{txt});
        }
        txts = bean.getTxt2();
        CommUtils.clearTable(txt2_table);
        for (String txt : txts) {
            CommUtils.table_AddItem(txt2_table, new Object[]{txt});
        }
    }

    @Override
    public Foot getBean() {
        CommUtils.stopTabelCellEditor(txt1_table);
        CommUtils.stopTabelCellEditor(txt2_table);
        Window owner=SwingUtilities.getWindowAncestor(root_pnl);
        Foot foot = new Foot();
        foot.setImage_winxin(weixin_tf.getText());
        for (int i = 0; i < txt1_table.getRowCount(); i++) {
            String value = (String) txt1_table.getValueAt(i, 0);
            if (CommUtils.isStrEmpty(value)) {
                CommUtils.showMsg(owner, "不能为空");
                txt1_table.setRowSelectionInterval(i, i);
                return null;
            }
            foot.addTxt1(value);
        }
        for (int i = 0; i < txt2_table.getRowCount(); i++) {
            String value = (String) txt2_table.getValueAt(i, 0);
            if (CommUtils.isStrEmpty(value)) {
                CommUtils.showMsg(owner, "不能为空");
                txt2_table.setRowSelectionInterval(i, i);
                return null;
            }
            foot.addTxt2(value);
        }
        return foot;
    }

    @Override
    public JPanel getRootPnl() {
        return root_pnl;
    }
}
