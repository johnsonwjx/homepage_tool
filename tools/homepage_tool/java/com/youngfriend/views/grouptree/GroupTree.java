package com.youngfriend.views.grouptree;

import com.youngfriend.views.grouptree.GroupBean;

import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeCellRenderer;
import javax.swing.tree.TreePath;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by pandongyu on 15/1/7.
 */
public class GroupTree extends JTree {
    private final DefaultTreeModel model;
    private final CheckBoxTreeNode rootNode;

    public GroupTree() {
        rootNode = new CheckBoxTreeNode("用户组");
        model = new DefaultTreeModel(rootNode);
        setModel(model);
        addMouseListener(new CheckBoxTreeNodeSelectionListener());
        setCellRenderer(new CheckBoxTreeCellRenderer());

    }

    public void initTree(List<GroupBean> beans) {
        if (beans == null || beans.isEmpty()) {
            return;
        }
        rootNode.removeAllChildren();
        CheckBoxTreeNode parent = null;
        for (GroupBean bean : beans) {
            String codeValue = bean.getCode();
            CheckBoxTreeNode newChild = new CheckBoxTreeNode(bean);
            if (parent != null && !parent.isRoot()) {
                while (parent != null && !parent.isRoot()) {
                    if (parent.isRoot()) {
                        break;
                    }
                    GroupBean pBean = (GroupBean) parent.getUserObject();
                    if (codeValue.startsWith(pBean.getCode())) {
                        break;
                    }
                    parent = (CheckBoxTreeNode) parent.getParent();
                }
            }
            if (parent == null) {
                parent = rootNode;
            }
            parent.add(newChild);
            parent = newChild;
        }
        model.reload();
    }

    public List<CheckBoxTreeNode> getSelectNode() {
        List<CheckBoxTreeNode> selects = new ArrayList<CheckBoxTreeNode>();
        getSelectNode(rootNode, selects);
        return selects;
    }

    private void getSelectNode(CheckBoxTreeNode root, List<CheckBoxTreeNode> selects) {
        if (root.isSelected) {
            selects.add(root);
            return;
        }
        if (root.getChildCount() <= 0) {
            return;
        }
        for (int i = 0; i < root.getChildCount(); i++) {
            CheckBoxTreeNode child = (CheckBoxTreeNode) root.getChildAt(i);
            getSelectNode(child, selects);
        }
    }


    class CheckBoxTreeNodeSelectionListener extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent event) {
            JTree tree = (JTree) event.getSource();
            int x = event.getX();
            int y = event.getY();
            int row = tree.getRowForLocation(x, y);
            TreePath path = tree.getPathForRow(row);
            if (path != null) {
                CheckBoxTreeNode node = (CheckBoxTreeNode) path.getLastPathComponent();
                if (node != null) {
                    boolean isSelected = !node.isSelected();
                    node.setSelected(isSelected);
                    ((DefaultTreeModel) tree.getModel()).nodeStructureChanged(node);
                }
            }
        }
    }

    class CheckBoxTreeCellRenderer extends JPanel implements TreeCellRenderer {
        protected JCheckBox check;
        protected CheckBoxTreeLabel label;

        public CheckBoxTreeCellRenderer() {
            setLayout(null);
            add(check = new JCheckBox());
            add(label = new CheckBoxTreeLabel());
            check.setBackground(UIManager.getColor("Tree.textBackground"));
            label.setForeground(UIManager.getColor("Tree.textForeground"));
        }

        /**
         * 返回的是一个<code>JPanel</code>对象，该对象中包含一个<code>JCheckBox</code>对象 和一个
         * <code>JLabel</code>对象。并且根据每个结点是否被选中来决定<code>JCheckBox</code> 是否	被选中。
         */
        @Override
        public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected, boolean expanded, boolean leaf, int row, boolean hasFocus) {
            String stringValue = tree.convertValueToText(value, selected, expanded, leaf, row, hasFocus);
            setEnabled(tree.isEnabled());
            check.setSelected(((CheckBoxTreeNode) value).isSelected());
            label.setFont(tree.getFont());
            label.setText(stringValue);
            label.setSelected(selected);
            label.setFocus(hasFocus);
            if (leaf)
                label.setIcon(UIManager.getIcon("Tree.leafIcon"));
            else if (expanded)
                label.setIcon(UIManager.getIcon("Tree.openIcon"));
            else
                label.setIcon(UIManager.getIcon("Tree.closedIcon"));

            return this;
        }

        @Override
        public Dimension getPreferredSize() {
            Dimension dCheck = check.getPreferredSize();
            Dimension dLabel = label.getPreferredSize();
            return new Dimension(dCheck.width + dLabel.width, dCheck.height < dLabel.height ? dLabel.height : dCheck.height);
        }

        @Override
        public void doLayout() {
            Dimension dCheck = check.getPreferredSize();
            Dimension dLabel = label.getPreferredSize();
            int yCheck = 0;
            int yLabel = 0;
            if (dCheck.height < dLabel.height)
                yCheck = (dLabel.height - dCheck.height) / 2;
            else
                yLabel = (dCheck.height - dLabel.height) / 2;
            check.setLocation(0, yCheck);
            check.setBounds(0, yCheck, dCheck.width, dCheck.height);
            label.setLocation(dCheck.width, yLabel);
            label.setBounds(dCheck.width, yLabel, dLabel.width, dLabel.height);
        }

        @Override
        public void setBackground(Color color) {
            if (color instanceof ColorUIResource)
                color = null;
            super.setBackground(color);
        }
    }

    class CheckBoxTreeLabel extends JLabel {
        private boolean isSelected;
        private boolean hasFocus;

        public CheckBoxTreeLabel() {
        }

        @Override
        public void setBackground(Color color) {
            if (color instanceof ColorUIResource)
                color = null;
            super.setBackground(color);
        }

        @Override
        public void paint(Graphics g) {
            String str;
            if ((str = getText()) != null) {
                if (0 < str.length()) {
                    if (isSelected)
                        g.setColor(UIManager.getColor("Tree.selectionBackground"));
                    else
                        g.setColor(UIManager.getColor("Tree.textBackground"));
                    Dimension d = getPreferredSize();
                    int imageOffset = 0;
                    Icon currentIcon = getIcon();
                    if (currentIcon != null)
                        imageOffset = currentIcon.getIconWidth() + Math.max(0, getIconTextGap() - 1);
                    g.fillRect(imageOffset, 0, d.width - 1 - imageOffset, d.height);
                    if (hasFocus) {
                        g.setColor(UIManager.getColor("Tree.selectionBorderColor"));
                        g.drawRect(imageOffset, 0, d.width - 1 - imageOffset, d.height - 1);
                    }
                }
            }
            super.paint(g);
        }

        @Override
        public Dimension getPreferredSize() {
            Dimension retDimension = super.getPreferredSize();
            if (retDimension != null)
                retDimension = new Dimension(retDimension.width + 3, retDimension.height);
            return retDimension;
        }

        public void setSelected(boolean isSelected) {
            this.isSelected = isSelected;
        }

        public void setFocus(boolean hasFocus) {
            this.hasFocus = hasFocus;
        }
    }
}
