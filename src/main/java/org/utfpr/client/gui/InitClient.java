package org.utfpr.client.gui;

import javax.swing.*;

public class InitClient extends JFrame {

    private JButton loginButton;
    private JButton logoutButton;
    private JButton createUserButton;
    private JPanel initPanel;

    public InitClient() {
        loginButton.addActionListener(e -> new Login().buildScreen());
        createUserButton.addActionListener(e -> new CreateUser().buildScreen());
        logoutButton.addActionListener(e -> new Logout().buildScreen());
    }

    public void buildScreen() {
        this.setContentPane(this.initPanel);
        this.setTitle("Inicio Client");
        this.setVisible(true);
        this.setSize(350, 200);
    }
}
