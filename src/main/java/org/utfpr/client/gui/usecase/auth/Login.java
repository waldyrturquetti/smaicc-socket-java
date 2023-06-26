package org.utfpr.client.gui.usecase.auth;

import org.utfpr.client.ClientApp;
import org.utfpr.client.auth.ClientSection;
import org.utfpr.client.exception.InvalidFieldException;
import org.utfpr.client.exception.ServerFailureException;
import org.utfpr.client.gui.usecase.UseCaseGui;
import org.utfpr.client.infra.ClientAppSocket;
import org.utfpr.client.util.ClientCheck;
import org.utfpr.common.gui.Dialogs;
import org.utfpr.common.util.Hash;
import org.utfpr.common.dto.auth.login.LoginDataClientToServer;
import org.utfpr.common.dto.auth.login.LoginDataServerToClient;
import org.utfpr.common.util.Status;

import javax.swing.*;
import java.io.IOException;
import java.util.Objects;

public class Login extends JFrame implements UseCaseGui {

    private JPanel loginPanel;
    private JTextField emailField;
    private JButton loginButton;
    private JPasswordField passwordField;
    private JButton backButton;
    private static Boolean isLoginCompleted = false;

    public Login() {
        backButton.addActionListener(e -> this.closeScreen());
        loginButton.addActionListener( e -> this.executeOperation());
    }

    @Override
    public void buildScreen() {
        this.setContentPane(this.loginPanel);
        this.setTitle("Login");
        this.setVisible(true);
        this.setSize(250, 250);
        isLoginCompleted = false;
    }

    @Override
    public void closeScreen() {
        if (isLoginCompleted) {
            Dialogs.showInfoMessage("Login feito com sucesso!", this);
            ClientApp.menuNonLogged.closeScreen();
            ClientApp.menuLogged.buildScreen();
        }
        this.setVisible(false);
    }

    @Override
    public void executeOperation() {
        try {
            this.send();
            this.returned();
            this.closeScreen();
        } catch (InvalidFieldException invalidFieldException) {
            System.err.println(invalidFieldException.getMessage());
            Dialogs.showWarningMessage(invalidFieldException.getMessage(), this);
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
            Dialogs.showErrorMessage(ex.getMessage(), this);
        }
    }

    private void send() {
        ClientCheck.checkEmail(emailField.getText());
        ClientCheck.checkPassword(new String(passwordField.getPassword()));

        LoginDataClientToServer loginData =
                new LoginDataClientToServer(emailField.getText(), Hash.encrypt(new String(passwordField.getPassword())));
        ClientAppSocket.sendMessage(loginData);
    }

    private void returned() throws IOException {
        LoginDataServerToClient loginDataServerToClient = (LoginDataServerToClient) ClientAppSocket.receiveMessage(LoginDataServerToClient.class);

        if (!Objects.equals(loginDataServerToClient.getStatus().trim(), Status.OK)) {
            throw new ServerFailureException(loginDataServerToClient.getStatus());
        }

        ClientSection.setId(loginDataServerToClient.getId());
        ClientSection.setName(loginDataServerToClient.getName());
        ClientSection.setToken(loginDataServerToClient.getToken());
        isLoginCompleted = true;
    }
}
