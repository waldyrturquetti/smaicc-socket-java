package org.utfpr.client.gui;

import org.utfpr.client.gui.usecase.CreateUser;
import org.utfpr.client.gui.usecase.Login;
import org.utfpr.client.gui.usecase.Logout;

import javax.swing.*;

public class MenuClient extends JFrame {

    private JButton loginButton;
    private JButton logoutButton;
    private JButton createUserButton;
    private JPanel initPanel;

    public MenuClient() {
        loginButton.addActionListener(e -> new Login().buildScreen());
        createUserButton.addActionListener(e -> new CreateUser().buildScreen());
        logoutButton.addActionListener(e -> new Logout().buildScreen());
    }

    public void buildScreen() {
        this.setContentPane(this.initPanel);
        this.setTitle("Menu Cliente");
        this.setVisible(true);
        this.setSize(250, 200);
    }
}
