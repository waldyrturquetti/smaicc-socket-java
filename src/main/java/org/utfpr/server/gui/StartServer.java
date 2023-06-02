package org.utfpr.server.gui;

import org.utfpr.common.gui.Dialogs;
import org.utfpr.common.gui.StartGui;
import org.utfpr.server.infra.ServerAppSocket;

import javax.swing.*;

public class StartServer extends JFrame implements StartGui {
    private JPanel startServerPanel;
    private JTextField portTextField;
    private JButton initButton;

    public StartServer() {
        this.initButton.addActionListener(e -> this.start());
    }

    @Override
    public void buildScreen() {
        this.setContentPane(this.startServerPanel);
        this.setTitle("Inciando Servidor");
        this.setVisible(true);
        this.setSize(350, 200);
    }

    @Override
    public void closeScreen() {
        this.setVisible(false);
    }

    @Override
    public void start() {
        try {
            this.closeScreen();
            ServerAppSocket.startSocket(Integer.parseInt(portTextField.getText()));
        } catch (Exception ex) {
            this.setVisible(true);
            Dialogs.showErrorMessage(ex.getMessage(), this);
        }
    }
}
