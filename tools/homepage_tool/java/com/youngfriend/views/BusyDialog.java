package com.youngfriend.views;

import javax.swing.*;
import java.awt.*;

public class BusyDialog extends JDialog {
    private JPanel contentPane;

    public BusyDialog(Window owner) {
        super(owner);
        setResizable(false);
        setLocationRelativeTo(owner);
        setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
        setContentPane(contentPane);
        setModal(true);
        pack();
    }
}
