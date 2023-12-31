package org.utfpr.client.gui.usecase.user;

import org.utfpr.client.ClientApp;
import org.utfpr.client.auth.ClientSection;
import org.utfpr.client.exception.InvalidFieldException;
import org.utfpr.client.exception.ServerFailureException;
import org.utfpr.client.gui.usecase.UseCaseGui;
import org.utfpr.client.gui.usecase.auth.Login;
import org.utfpr.client.infra.ClientAppSocket;
import org.utfpr.client.util.ClientCheck;
import org.utfpr.common.dto.common.CommonDataServerToClient;
import org.utfpr.common.dto.user.updateUser.UpdateUserDataClientToServer;
import org.utfpr.common.gui.Dialogs;
import org.utfpr.common.util.Hash;
import org.utfpr.common.util.Status;

import javax.swing.*;
import java.io.IOException;
import java.util.Objects;

public class UpdateUser extends JFrame implements UseCaseGui {
    private JTextField nameField;
    private JTextField emailField;
    private JPasswordField passwordField;
    private JButton updateButton;
    private JPanel updateUserPanel;

    public UpdateUser() {
        updateButton.addActionListener(e -> this.executeOperation());
    }

    public void buildScreen() {
        this.setContentPane(this.updateUserPanel);
        this.setTitle("Criar Usário");
        this.setVisible(true);
        this.setSize(350, 300);
    }

    @Override
    public void closeScreen() {
        Dialogs.showInfoMessage("Usuário Atualizado com Sucesso!!");
        this.setVisible(false);
        ClientApp.menuLogged.closeScreen();
        ClientApp.menuNonLogged.buildScreen();
        new Login().buildScreen();
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
        ClientCheck.checkName(this.nameField.getText());
        ClientCheck.checkEmail(this.emailField.getText());
        ClientCheck.checkPassword(new String(passwordField.getPassword()));

        UpdateUserDataClientToServer updateUserDataClientToServer =
                new UpdateUserDataClientToServer(ClientSection.getId(), ClientSection.getToken(),
                        nameField.getText(), emailField.getText(), Hash.encrypt(new String(passwordField.getPassword())));

        ClientAppSocket.sendMessage(updateUserDataClientToServer);
    }

    private void returned() throws IOException {
        CommonDataServerToClient commonDataServerToClient = (CommonDataServerToClient) ClientAppSocket.receiveMessage(CommonDataServerToClient.class);

        if (!Objects.equals(commonDataServerToClient.getStatus().trim(), Status.OK)) {
            throw new ServerFailureException(commonDataServerToClient.getStatus());
        }
    }
}
