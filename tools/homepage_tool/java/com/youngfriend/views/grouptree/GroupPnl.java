package com.youngfriend.views.grouptree;

import com.youngfriend.utils.CommUtils;
import com.youngfriend.utils.ServiceUtils;
import com.youngfriend.views.Editor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;

/**
 * Created by pandongyu on 15/1/7.
 */
public class GroupPnl implements Editor {
    private JPanel rootPnl;
    private JButton ok_btn;
    private JButton cancel_btn;
    private JScrollPane scrolpnl;
    private GroupTree groupTree;
    private boolean sumbit = false;
    private JDialog dialog = null;
    private HashMap<String, Object> map;

    public GroupPnl() {
        groupTree = new GroupTree();
        scrolpnl.setViewportView(groupTree);
        ok_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                sumbit = true;
                List<CheckBoxTreeNode> selects = groupTree.getSelectNode();
                StringBuilder sb = new StringBuilder();
                if (selects != null || !selects.isEmpty()) {
                    for (CheckBoxTreeNode node : selects) {
                        if (node.isRoot()) {
                            break;
                        }
                        GroupBean bean = (GroupBean) node.getUserObject();
                        sb.append(bean.getId()).append(",");
                    }
                    if (sb.length() > 0) {
                        sb.deleteCharAt(sb.length() - 1);
                    }
                }
                map.put("groupid", sb.toString());
                dialog.dispose();
            }
        });
        cancel_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                dialog.dispose();
            }
        });
    }


    @Override
    public void eidt(Window owner, HashMap<String, Object> map) {
        sumbit = false;
        List<GroupBean> beans = ServiceUtils.getUserGroup((String) map.get("url"));
        groupTree.initTree(beans);
        this.map = map;
        dialog = CommUtils.getDialog(owner, "选择用户组", rootPnl);
        dialog.setVisible(true);
    }

    @Override
    public boolean isSubmit() {
        return sumbit;
    }
}
