package org.utfpr.client.gui;

import org.utfpr.client.exception.ProblemWithServerConnectionException;
import org.utfpr.client.infra.ClientSocket;

import javax.swing.*;

public class StartClient extends JFrame {
    private JTextField hostTextField;
    private JTextField portaTextField;
    private JButton conectarButton;
    private JPanel startClientPanel;

    public StartClient() {
        this.conectarButton.addActionListener(actionEvent -> {
            try {
                ClientSocket.startConnect(this.hostTextField.getText(), Integer.parseInt(this.portaTextField.getText()));
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
