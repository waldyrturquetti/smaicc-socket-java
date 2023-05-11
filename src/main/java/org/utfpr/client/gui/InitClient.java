package org.utfpr.client.gui;

import org.utfpr.client.gui.usecase.CreateUser;
import org.utfpr.client.gui.usecase.Login;
import org.utfpr.client.gui.usecase.Logout;

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
        this.setTitle("Início Cliente");
        this.setVisible(true);
        this.setSize(250, 200);
    }
}
