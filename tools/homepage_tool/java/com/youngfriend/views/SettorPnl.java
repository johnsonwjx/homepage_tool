package com.youngfriend.views;

import javax.swing.*;
import java.awt.*;

public class SettorPnl extends JPanel {
    private JButton button;

    public SettorPnl(boolean editable) {
        setLayout(new BorderLayout(0, 0));
        button = new JButton("...");
        button.setPreferredSize(new Dimension(20, 0));
        add(button, BorderLayout.EAST);
        textField = new JTextField();
        add(textField, BorderLayout.CENTER);
        textField.setEditable(editable);
        textField.setColumns(10);
    }

    public JButton getBtn() {
        return button;
    }

    private static final long serialVersionUID = 1L;
    private JTextField textField;

    public void setValue(Object value) {
        textField.setText((String) value);
    }

    public String getValue() {
        return textField.getText();
    }

}
