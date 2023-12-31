package org.utfpr.client.gui.menu;

import org.utfpr.client.gui.usecase.incident.CreateIncident;
import org.utfpr.client.gui.usecase.auth.Logout;
import org.utfpr.client.gui.usecase.incident.GetIncidents;
import org.utfpr.client.gui.usecase.incident.GetIncidentsByUser;
import org.utfpr.client.gui.usecase.user.DeleteUser;
import org.utfpr.client.gui.usecase.user.UpdateUser;
import org.utfpr.common.gui.interfaces.Gui;

import javax.swing.*;

public class MenuLogged extends JFrame implements Gui {

    private JPanel initPanel;
    private JButton createIncidentButton;
    private JButton getIncidentsButton;
    private JButton logoutButton;
    private JButton updateUserButton;
    private JButton myIncidentsButton;
    private JButton deleteUserButton;

    public MenuLogged() {
        this.createIncidentButton.addActionListener(e -> new CreateIncident().buildScreen());
        this.getIncidentsButton.addActionListener(e -> new GetIncidents().buildScreen());
        this.logoutButton.addActionListener(e -> new Logout().buildScreen());
        this.updateUserButton.addActionListener(e -> new UpdateUser().buildScreen());
        this.myIncidentsButton.addActionListener(e -> new GetIncidentsByUser().buildScreen());
        this.deleteUserButton.addActionListener(e -> new DeleteUser().buildScreen());
    }

    @Override
    public void buildScreen() {
        this.setContentPane(this.initPanel);
        this.setTitle("Menu Cliente (Logado)");
        this.setVisible(true);
        this.setSize(350, 300);
    }

    @Override
    public void closeScreen() {
        this.setVisible(false);
    }
}
