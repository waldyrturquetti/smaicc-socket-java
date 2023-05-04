package org.utfpr.client.gui;

import org.utfpr.client.infra.ClientSocket;
import org.utfpr.common.dto.auth.login.LoginDataClientToServer;
import org.utfpr.common.dto.auth.login.LoginDataServerToClient;

import javax.swing.*;
import java.io.IOException;

public class Login extends JFrame {
    private JTextField emailField;
    private JButton loginButton;
    private JPasswordField passwordField;
    private JButton backButton;
    private JPanel loginPanel;

    public Login() {
        backButton.addActionListener(e -> this.setVisible(false));
        loginButton.addActionListener( e -> {
            try {
                LoginDataClientToServer loginData = new LoginDataClientToServer(emailField.getText(), new String(passwordField.getPassword()));
                ClientSocket.sendMessage(loginData);
                this.setVisible(false);
                this.returned();
            } catch (Exception ex) {
                System.err.println(ex.getMessage());
                new ErrorScreen().buildScreen(ex.getMessage());
            }
        });
    }

    public void buildScreen() {
        this.setContentPane(this.loginPanel);
        this.setTitle("Login");
        this.setVisible(true);
        this.setSize(350, 250);
    }

    private void returned() throws IOException {
        LoginDataServerToClient loginDataServerToClient = (LoginDataServerToClient) ClientSocket.receiveMessage(new LoginDataClientToServer());
    }

}
