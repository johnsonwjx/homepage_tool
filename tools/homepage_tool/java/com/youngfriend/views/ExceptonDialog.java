package com.youngfriend.views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ExceptonDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JTextPane msg;

    private ExceptonDialog(Window owner,String error) {
        super(owner);
        setTitle("错误");
        msg.setText(error);
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });
        setVisible(true);
    }

    private void onOK() {
// add your code here
        dispose();
    }

}
