package org.utfpr.client.gui;

import org.utfpr.client.gui.usecase.CreateIncident;
import org.utfpr.client.gui.usecase.Logout;

import javax.swing.*;

public class MenuLogged extends JFrame {

    private JPanel initPanel;
    private JButton createIncidentButton;
    private JButton getIncidentsButton;
    private JButton logoutButton;

    public MenuLogged() {
        createIncidentButton.addActionListener(e -> new CreateIncident().buildScreen());
        logoutButton.addActionListener(e -> new Logout().buildScreen());
    }

    public void buildScreen() {
        this.setContentPane(this.initPanel);
        this.setTitle("Menu Cliente (Logado)");
        this.setVisible(true);
        this.setSize(250, 200);
    }

    public void closeScreen() {
        this.setVisible(false);
    }
}
