package org.utfpr.client.gui.menu;

import org.utfpr.client.gui.usecase.incident.GetIncidents;
import org.utfpr.client.gui.usecase.user.CreateUser;
import org.utfpr.client.gui.usecase.auth.Login;
import org.utfpr.common.gui.Gui;

import javax.swing.*;

public class MenuNonLogged extends JFrame implements Gui {

    private JButton loginButton;
    private JButton createUserButton;
    private JPanel initPanel;
    private JButton getIncidentsButton;

    public MenuNonLogged() {
        loginButton.addActionListener(e -> new Login().buildScreen());
        createUserButton.addActionListener(e -> new CreateUser().buildScreen());
        getIncidentsButton.addActionListener(e -> new GetIncidents().buildScreen());
    }

    @Override
    public void buildScreen() {
        this.setContentPane(this.initPanel);
        this.setTitle("Menu Cliente (Não Logado)");
        this.setSize(350, 200);
        this.setVisible(true);
    }

    @Override
    public void closeScreen() {
        this.setVisible(false);
    }
}
