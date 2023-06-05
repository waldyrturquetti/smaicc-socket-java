package org.utfpr.server.gui;

import org.utfpr.common.gui.Dialogs;
import org.utfpr.common.gui.StartGui;
import org.utfpr.server.infra.thread.ServerSocketThread;

import javax.swing.*;

public class StartServer extends JFrame implements StartGui {
    private JPanel startServerPanel;
    private JTextField portTextField;
    private JButton initButton;
    private JButton stopButton;
    private ServerLog serverLog;

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
            this.serverLog = new ServerLog(portTextField.getText());
            ServerSocketThread.startSocket(Integer.parseInt(portTextField.getText()), this.serverLog);
            this.serverLog.buildScreen();
        } catch (Exception ex) {
            this.setVisible(true);
            Dialogs.showErrorMessage(ex.getMessage(), this);
        }
    }

    @Override
    public void stop() {
        this.serverLog.closeScreen();
        ServerSocketThread.closeSocket();
    }
}
