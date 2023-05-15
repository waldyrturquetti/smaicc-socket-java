package org.utfpr.server.gui;

import org.utfpr.common.gui.Dialogs;
import org.utfpr.server.infra.ServerAppSocket;

import javax.swing.*;
import java.awt.*;

public class StartServer extends JFrame {
    private JPanel startServerPanel;
    private JTextField portTextField;
    private JButton initButton;

    public StartServer() {
        this.initButton.addActionListener(e -> {
            try {
                this.setVisible(false);
                ServerAppSocket.startSocket(Integer.parseInt(portTextField.getText()));
            } catch (Exception ex) {
                this.setVisible(true);
                Dialogs.showErrorMessage(ex.getMessage(), this);
            }
        });
    }

    public void buildScreen() {
        this.setContentPane(this.startServerPanel);
        this.setTitle("Inciando Servidor");
        this.setVisible(true);
        this.setSize(350, 200);
    }
}
