package org.utfpr.client.gui.usecase.auth;

import org.utfpr.client.ClientApp;
import org.utfpr.client.auth.ClientSection;
import org.utfpr.client.exception.EmptyFieldException;
import org.utfpr.client.exception.ServerFailureException;
import org.utfpr.client.infra.ClientAppSocket;
import org.utfpr.common.gui.Dialogs;
import org.utfpr.common.util.Hash;
import org.utfpr.common.dto.auth.login.LoginDataClientToServer;
import org.utfpr.common.dto.auth.login.LoginDataServerToClient;
import org.utfpr.common.util.Status;

import javax.swing.*;
import java.io.IOException;
import java.util.Objects;

public class Login extends JFrame {
    private JTextField emailField;
    private JButton loginButton;
    private JPasswordField passwordField;
    private JButton backButton;
    private JPanel loginPanel;

    public Login() {
        backButton.addActionListener(e -> this.setVisible(false));
        loginButton.addActionListener( e -> {
            try {
                if (emailField.getText().isBlank() || (new String(passwordField.getPassword()).isBlank())) {
                    throw new EmptyFieldException();
                }

                LoginDataClientToServer loginData =
                        new LoginDataClientToServer(emailField.getText(), Hash.encrypt(new String(passwordField.getPassword())));
                ClientAppSocket.sendMessage(loginData);
                this.returned();
                Dialogs.showInfoMessage("Login feito com sucesso!", this);
                this.setVisible(false);
                ClientApp.menuNonLogged.closeScreen();
                ClientApp.menuLogged.buildScreen();
            } catch (Exception ex) {
                System.err.println(ex.getMessage());
                Dialogs.showErrorMessage(ex.getMessage(), this);
            }
        });
    }

    public void buildScreen() {
        this.setContentPane(this.loginPanel);
        this.setTitle("Login");
        this.setVisible(true);
        this.setSize(250, 250);
    }

    private void returned() throws IOException {
        LoginDataServerToClient loginDataServerToClient = (LoginDataServerToClient) ClientAppSocket.receiveMessage(LoginDataServerToClient.class);

        if (!Objects.equals(loginDataServerToClient.getStatus().trim(), Status.OK)) {
            throw new ServerFailureException(loginDataServerToClient.getStatus());
        }

        ClientSection.setId(loginDataServerToClient.getId());
        ClientSection.setName(loginDataServerToClient.getName());
        ClientSection.setToken(loginDataServerToClient.getToken());
    }
}
