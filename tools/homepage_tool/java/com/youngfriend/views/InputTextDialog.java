package com.youngfriend.views;

import com.youngfriend.utils.CommUtils;
import com.youngfriend.utils.MyValidator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class InputTextDialog extends JDialog {
    private  MyValidator<String> validator;
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField textField1;
    private boolean ok=false;

    public InputTextDialog(Window owner,String title,MyValidator<String> validator) {
        super(owner,ModalityType.APPLICATION_MODAL);
        setTitle(title);
        this.validator=validator;
        setContentPane(contentPane);
        getRootPane().setDefaultButton(buttonOK);
        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

// call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

// call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        pack();
        setLocationRelativeTo(owner);
        setVisible(true);
    }
    public String getTxt(){
        return textField1.getText().trim() ;
    }
    private void onOK() {
        if(CommUtils.isStrEmpty(textField1.getText())){
            CommUtils.showMsg(this,"请输入");
            return ;
        }
        if(validator!=null){
            if(!validator.validato(this,textField1.getText())){
                return ;
            }
        }
        ok=true;
        dispose();
    }
    public InputTextDialog(Window owner) {
        this(owner,"",null);
    }

    public boolean isOk() {
        return ok;
    }

    private void onCancel() {
// add your code here if necessary
        ok=false;
        dispose();
    }
}
