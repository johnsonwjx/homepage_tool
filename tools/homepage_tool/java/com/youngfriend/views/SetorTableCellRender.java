package com.youngfriend.views;
import com.youngfriend.utils.CommUtils;
import com.youngfriend.views.Do4Obj;
import com.youngfriend.views.PropTableRender;
import com.youngfriend.views.SettorPnl;

import javax.swing.*;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class SetorTableCellRender implements TableCellRenderer, PropTableRender {
    private SettorPnl pnl = null;
    private SetorCellEditor editor;

    public SetorTableCellRender(final Do4Obj<SettorPnl> action) {
        pnl = new SettorPnl(false);
        editor = new SetorCellEditor(action);
    }



    @Override
    public TableCellEditor getTableCellEditor() {
        return editor;
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        if (isSelected) {
            pnl.setBackground(UIManager.getColor("controlHighlight"));
        } else {
            pnl.setBackground(UIManager.getColor("control"));
        }
        pnl.setValue(value);
        return pnl;
    }

    class SetorCellEditor extends AbstractCellEditor implements TableCellEditor {
        private SettorPnl pnl = new SettorPnl(true);
        private JTable table;

        public SetorCellEditor(final Do4Obj<SettorPnl> action) {
            pnl.getBtn().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    action.doAction(pnl);
                    CommUtils.stopTabelCellEditor(table);
                }
            });
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean select, int row, int column) {
            this.table = table;
            pnl.setValue(value);
            return pnl;
        }

        @Override
        public Object getCellEditorValue() {
            return pnl.getValue();
        }
    }
}