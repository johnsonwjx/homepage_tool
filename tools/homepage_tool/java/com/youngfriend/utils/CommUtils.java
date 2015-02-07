package com.youngfriend.utils;


import com.youngfriend.utils.Global;
import com.youngfriend.views.NameValDto;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Map;

/**
 * Created by pandongyu on 14/12/30.
 */
public class CommUtils {
    public static void showMsg(Window owner, String msg) {
        JOptionPane.showMessageDialog(owner, msg);
    }

    private static Logger logger = LogManager.getLogger(CommUtils.class);

    public static boolean showConfirm(Window owner, String msg) {
        return (JOptionPane.OK_OPTION == JOptionPane.showConfirmDialog(owner, msg, "提示", JOptionPane.OK_CANCEL_OPTION));
    }

    public static void stopTabelCellEditor(JTable table) {
        if (table.getRowCount() <= 0) {
            return;
        }
        TableCellEditor editor = table.getCellEditor();
        if (editor != null) {
            if (table.isEditing()) {
                editor.stopCellEditing();
            }
        }
    }


    public static void clearTable(JTable table) {
        if (table.getRowCount() <= 0) {
            return;
        }
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
    }

    public static void table_AddItem(JTable table, Object... item_add) {
        stopTabelCellEditor(table);
        int row = table.getSelectedRow();
        DefaultTableModel table_model = (DefaultTableModel) table.getModel();
        if (row == -1) {
            row = table_model.getRowCount();
        } else {
            row++;
        }
        if (item_add == null) {
            item_add = new Object[]{};
        }
        table_model.addRow(item_add);
        table.setRowSelectionInterval(row, row);
    }

    public static void table_DellItem(JTable table) {
        stopTabelCellEditor(table);
        int row = table.getSelectedRow();
        if (row == -1) {
            return;
        }
        stopTabelCellEditor(table);
        DefaultTableModel table_model = (DefaultTableModel) table.getModel();
        table_model.removeRow(row);
        row--;
        if (row < 0) {
            row = table_model.getRowCount() - 1;
        }
        if (row < 0) {
            return;
        }
        table.setRowSelectionInterval(row, row);
    }

    public static boolean isStrEmpty(String str) {
        if (str == null) {
            return true;
        }
        if (str.trim().length() <= 0) {
            return true;
        }
        return false;
    }

    public static JTable createTable(String... titles) {
        JTable table = new JTable();
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.getTableHeader().setReorderingAllowed(false);
        table.setModel(new DefaultTableModel(titles, 0));
        table.setRowHeight(20);
        return table;
    }

    public static void after_selectFile(File file, String old) {
        if (file == null) {
            return;
        }
        if (!file.exists()) {
            return;
        }
        try {
            FileUtils.copyFileToDirectory(file, new File(Global.getProject_images_path()));
            if (old != null) {
                File file_old = new File(old);
                file_old.deleteOnExit();
            }
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
    }

    public static String getTypeName(String value) {
        if (value == null) {
            return "";
        }
        if (!Global.tabTypeMap.containsValue(value)) {
            return "";
        }
        for (Map.Entry<String, String> entry : Global.tabTypeMap.entrySet()) {
            if (entry.getValue().equals(value)) {
                return entry.getKey();
            }
        }
        return "";
    }

    public static JComboBox createCombo(Map<String, String> itemMap) {
        JComboBox combo = new JComboBox();
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        for (Map.Entry<String, String> item : itemMap.entrySet()) {
            model.addElement(new NameValDto(item.getKey(), item.getValue()));
        }
        combo.setModel(model);
        return combo;
    }

    public static JDialog getDialog(Window owner, String title, JComponent com) {
        JDialog dialog = new JDialog(owner, title, Dialog.ModalityType.APPLICATION_MODAL);
        dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        dialog.getContentPane().add(com);
        dialog.setResizable(true);
        dialog.setLocationRelativeTo(owner);
        dialog.pack();
        return dialog;
    }

    public static String[] getImageExt() {
        return new String[]{"jpg", "gif", "png"};
    }

    private static JFileChooser chooser = new JFileChooser();

    public static File getSelectFile(JPanel panel, String desc, String... exts) {
        chooser.resetChoosableFileFilters();
        chooser.setFileFilter(new FileNameExtensionFilter(desc, exts));
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        if (JFileChooser.APPROVE_OPTION == chooser.showOpenDialog(SwingUtilities.getWindowAncestor(panel))) {
            return chooser.getSelectedFile();
        }
        return null;
    }

    public static void busydoing() {
        SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                return null;
            }
            @Override
            protected void done() {
                super.done();
            }
        };
        worker.execute();

    }

}
