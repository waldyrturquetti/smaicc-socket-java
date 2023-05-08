package org.utfpr.client.gui;

import javax.swing.*;

public class Logout extends JFrame {
    private JPanel logoutPanel;
    private JButton yesButton;
    private JButton noButton;

    public Logout() {
        noButton.addActionListener(e -> this.setVisible(false));
        yesButton.addActionListener(e -> {

        });
    }

    public void buildScreen() {
        this.setContentPane(this.logoutPanel);
        this.setTitle("Logout");
        this.setVisible(true);
        this.setSize(350, 250);
    }
}
