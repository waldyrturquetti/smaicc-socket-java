package org.utfpr.client.gui;

import org.utfpr.client.exception.ServerFailureException;
import org.utfpr.client.infra.ClientSocket;
import org.utfpr.common.dto.common.CommonDataServerToClient;
import org.utfpr.common.dto.user.createUser.CreateUserDataClientToServer;
import org.utfpr.common.util.Status;

import javax.swing.*;
import java.io.IOException;
import java.util.Objects;

public class CreateUser extends JFrame {
    private JTextField nameTextField;
    private JTextField emailTextField;
    private JPasswordField passwordField;
    private JButton registerButton;
    private JButton backButton;
    private JPanel createUserPanel;

    public CreateUser() {
        this.backButton.addActionListener(e -> this.setVisible(false));
        this.registerButton.addActionListener(e -> {
            try {
                CreateUserDataClientToServer createUserData = new CreateUserDataClientToServer(
                        this.nameTextField.getText(), this.emailTextField.getText(), new String(this.passwordField.getPassword()));
                ClientSocket.sendMessage(createUserData);
                this.returned();
                this.setVisible(false);
                new Login().buildScreen();
            } catch (Exception ex) {
                System.err.println(ex.getMessage());
                new ErrorScreen().buildScreen(ex.getMessage());
            }
        });
    }

    public void buildScreen() {
        this.setContentPane(this.createUserPanel);
        this.setTitle("Criar Usário");
        this.setVisible(true);
        this.setSize(350, 300);
    }

    private void returned() throws IOException {
        CommonDataServerToClient commonDataServerToClient = (CommonDataServerToClient) ClientSocket.receiveMessage(new CommonDataServerToClient());

        if (!Objects.equals(commonDataServerToClient.getStatus().trim(), Status.OK)) {
            throw new ServerFailureException(commonDataServerToClient.getStatus());
        }
    }
}
