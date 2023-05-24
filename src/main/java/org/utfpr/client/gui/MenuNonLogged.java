package org.utfpr.client.gui;

import org.utfpr.client.gui.usecase.CreateUser;
import org.utfpr.client.gui.usecase.Login;

import javax.swing.*;

public class MenuNonLogged extends JFrame {

    private JButton loginButton;
    private JButton createUserButton;
    private JPanel initPanel;

    public MenuNonLogged() {
        loginButton.addActionListener(e -> new Login().buildScreen());
        createUserButton.addActionListener(e -> new CreateUser().buildScreen());
    }

    public void buildScreen() {
        this.setContentPane(this.initPanel);
        this.setTitle("Menu Cliente (Não Logado)");
        this.setSize(250, 200);
        this.setVisible(true);
    }

    public void closeScreen() {
        this.setVisible(false);
    }
}
