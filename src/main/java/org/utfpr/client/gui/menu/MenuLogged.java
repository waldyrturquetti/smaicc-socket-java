package org.utfpr.client.gui.menu;

import org.utfpr.client.gui.usecase.incident.CreateIncident;
import org.utfpr.client.gui.usecase.auth.Logout;
import org.utfpr.client.gui.usecase.user.UpdateUser;

import javax.swing.*;

public class MenuLogged extends JFrame {

    private JPanel initPanel;
    private JButton createIncidentButton;
    private JButton getIncidentsButton;
    private JButton logoutButton;
    private JButton updateUserButton;

    public MenuLogged() {
        createIncidentButton.addActionListener(e -> new CreateIncident().buildScreen());
        logoutButton.addActionListener(e -> new Logout().buildScreen());
        updateUserButton.addActionListener(e -> new UpdateUser().buildScreen());
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
