package com.youngfriend.views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShowDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JTextPane textPane1;

    public ShowDialog(Window owner,String title ,String txt) {
        super(owner);
        setLocationRelativeTo(owner);
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });
        setTitle(title);
        textPane1.setText(txt);
        pack();
        setVisible(true);
    }

    private void onOK() {
// add your code here
        dispose();
    }


}
