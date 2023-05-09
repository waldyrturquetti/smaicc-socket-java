package org.utfpr.client.gui;

import org.utfpr.client.exception.ProblemWithServerConnectionException;
import org.utfpr.client.infra.ClientSocket;

import javax.swing.*;

public class StartClient extends JFrame {
    private JTextField hostTextField;
    private JTextField portTextField;
    private JButton connectButton;
    private JPanel startClientPanel;

    public StartClient() {
        this.connectButton.addActionListener(actionEvent -> {
            try {
                ClientSocket.setServerHostname(this.hostTextField.getText());
                ClientSocket.setPort(Integer.parseInt(this.portTextField.getText()));
                this.setVisible(false);
                new InitClient().buildScreen();
            } catch (ProblemWithServerConnectionException e) {
                System.err.println(e.getMessage());
                new ErrorScreen().buildScreen(e.getMessage());
            }
        });
    }

    public void buildScreen() {
        this.setContentPane(this.startClientPanel);
        this.setTitle("Inciando Cliente");
        this.setVisible(true);
        this.setSize(350, 200);
    }
}
