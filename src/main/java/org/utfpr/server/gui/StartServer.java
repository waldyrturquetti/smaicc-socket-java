package org.utfpr.server.gui;

import org.utfpr.common.gui.Dialogs;
import org.utfpr.common.gui.interfaces.StartGui;
import org.utfpr.server.infra.thread.ServerSocketThread;
import org.utfpr.server.infra.util.ServerInfra;

import javax.swing.*;

public class StartServer extends JFrame implements StartGui {
    private JPanel startServerPanel;
    private JTextField portTextField;
    private JButton initButton;
    private JButton stopButton;

    public StartServer() {
        this.initButton.addActionListener(e -> this.start());
        this.stopButton.addActionListener(e -> this.stop());
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
            ServerSocketThread.startSocket(Integer.parseInt(portTextField.getText()));
            ServerInfra.getServerLog().buildScreen();
        } catch (Exception ex) {
            this.setVisible(true);
            Dialogs.showErrorMessage(ex.getMessage(), this);
        }
    }

    @Override
    public void stop() {
        ServerInfra.getServerLog().closeScreen();
        ServerSocketThread.closeSocket();
    }
}
