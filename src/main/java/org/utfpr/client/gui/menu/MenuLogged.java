package org.utfpr.client.gui.menu;

import org.utfpr.client.gui.usecase.incident.CreateIncident;
import org.utfpr.client.gui.usecase.auth.Logout;
import org.utfpr.client.gui.usecase.incident.GetIncidents;
import org.utfpr.client.gui.usecase.user.UpdateUser;

import javax.swing.*;

public class MenuLogged extends JFrame {

    private JPanel initPanel;
    private JButton createIncidentButton;
    private JButton getIncidentsButton;
    private JButton logoutButton;
    private JButton updateUserButton;

    public MenuLogged() {
        this.createIncidentButton.addActionListener(e -> new CreateIncident().buildScreen());
        this.getIncidentsButton.addActionListener(e -> new GetIncidents().buildScreen());
        this.logoutButton.addActionListener(e -> new Logout().buildScreen());
        this.updateUserButton.addActionListener(e -> new UpdateUser().buildScreen());
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
