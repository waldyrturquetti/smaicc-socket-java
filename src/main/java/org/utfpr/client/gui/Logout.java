package org.utfpr.client.gui;

import org.utfpr.client.auth.ClientSection;
import org.utfpr.client.exception.ServerFailureException;
import org.utfpr.client.infra.ClientSocket;
import org.utfpr.common.dto.auth.logout.LogoutDataClientToServer;
import org.utfpr.common.dto.common.CommonDataServerToClient;
import org.utfpr.common.util.Status;

import javax.swing.*;
import java.io.IOException;
import java.util.Objects;

public class Logout extends JFrame {
    private JPanel logoutPanel;
    private JButton yesButton;
    private JButton noButton;

    public Logout() {
        noButton.addActionListener(e -> this.setVisible(false));
        yesButton.addActionListener(e -> {
            try {
                LogoutDataClientToServer logoutData = new LogoutDataClientToServer(ClientSection.getId(), ClientSection.getToken());
                ClientSocket.sendMessage(logoutData);
                this.returned();
                this.setVisible(false);
            } catch (Exception ex) {
                System.err.println(ex.getMessage());
                new ErrorScreen().buildScreen(ex.getMessage());
            }
        });
    }

    public void buildScreen() {
        this.setContentPane(this.logoutPanel);
        this.setTitle("Logout");
        this.setVisible(true);
        this.setSize(350, 250);
    }

    private void returned() throws IOException {
        CommonDataServerToClient commonDataServerToClient = (CommonDataServerToClient) ClientSocket.receiveMessage(new CommonDataServerToClient());

        if (!Objects.equals(commonDataServerToClient.getStatus().trim(), Status.OK)) {
            throw new ServerFailureException(commonDataServerToClient.getStatus());
        }

        ClientSection.setId(null);
        ClientSection.setName(null);
        ClientSection.setToken(null);
    }
}
