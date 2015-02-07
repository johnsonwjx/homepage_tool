package com.youngfriend.views;

import com.youngfriend.views.PropTableRender;

import javax.swing.*;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class NumTableCellRender implements TableCellRenderer, PropTableRender {
    private JSpinner spin = new JSpinner();
    private TableCellEditor editor = null;

    @Override
    public TableCellEditor getTableCellEditor() {
        if (editor == null) {
            editor = new NumberCellEditor();
        }
        return editor;
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        if (isSelected) {
            spin.setBackground(UIManager.getColor("controlHighlight"));
        } else {
            spin.setBackground(UIManager.getColor("control"));
        }
        if (value == null) {
            value = 0;
            table.setValueAt(value,row,column);
        }
        spin.setValue(value);
        return spin;
    }

    class NumberCellEditor extends AbstractCellEditor implements TableCellEditor {
        private static final long serialVersionUID = 1L;
        private JSpinner spin = new JSpinner();


        @Override
        public Object getCellEditorValue() {
            return spin.getValue();
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            value = table.getValueAt(row, column);
            if (value == null) {
                value = 0;
            }
            spin.setValue(value);
            return spin;
        }
    }

}
