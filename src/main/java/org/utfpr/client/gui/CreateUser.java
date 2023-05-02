package org.utfpr.client.gui;

import javax.swing.*;

public class CreateUser extends JFrame {
    private JTextField nameTextField;
    private JTextField emailTextField;
    private JPasswordField passwordField;
    private JButton registerButton;
    private JButton backButton;
    private JPanel createUserPanel;

    public void buildScreen() {
        this.setContentPane(this.createUserPanel);
        this.setTitle("Criar Usário");
        this.setVisible(true);
        this.setSize(350, 300);
    }
}
