package org.utfpr.client.gui;

import javax.swing.*;

public class Login extends JFrame {
    private JTextField emailField;
    private JButton loginButton;
    private JPasswordField passwordField;
    private JButton backButton;
    private JPanel loginPanel;

    public void buildScreen() {
        this.setContentPane(this.loginPanel);
        this.setTitle("Login");
        this.setVisible(true);
        this.setSize(350, 250);
    }
}
