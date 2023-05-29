package org.utfpr.client.gui.menu;

import org.utfpr.client.ClientApp;
import org.utfpr.client.exception.ProblemWithServerConnectionException;
import org.utfpr.client.infra.ClientAppSocket;
import org.utfpr.common.gui.Dialogs;

import javax.swing.*;

public class StartClient extends JFrame {
    private JTextField hostTextField;
    private JTextField portTextField;
    private JButton connectButton;
    private JPanel startClientPanel;

    public StartClient() {
        this.connectButton.addActionListener(actionEvent -> {
            try {
                ClientAppSocket.setServerHostname(this.hostTextField.getText());
                ClientAppSocket.setPort(Integer.parseInt(this.portTextField.getText()));
                this.setVisible(false);
                ClientApp.menuNonLogged.buildScreen();
            } catch (ProblemWithServerConnectionException e) {
                System.err.println(e.getMessage());
                Dialogs.showErrorMessage(e.getMessage(), this);
            }
        });
    }

    public void buildScreen() {
        this.setContentPane(this.startClientPanel);
        this.setTitle("Inciando Cliente");
        this.setVisible(true);
        this.setSize(350, 200);
    }
}
