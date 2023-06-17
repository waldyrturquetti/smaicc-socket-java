package org.utfpr.client.gui.usecase.user;

import org.utfpr.client.ClientApp;
import org.utfpr.client.auth.ClientSection;
import org.utfpr.client.exception.InvalidFieldException;
import org.utfpr.client.exception.ServerFailureException;
import org.utfpr.client.gui.usecase.UseCaseGui;
import org.utfpr.client.infra.ClientAppSocket;
import org.utfpr.client.util.ClientCheck;
import org.utfpr.common.dto.common.CommonDataServerToClient;
import org.utfpr.common.dto.user.deleteUser.DeleteUserDataClientToServer;
import org.utfpr.common.gui.Dialogs;
import org.utfpr.common.util.Status;

import javax.swing.*;
import java.io.IOException;
import java.util.Objects;

public class DeleteUser extends JFrame implements UseCaseGui {
    private JPanel deleteUserPanel;
    private JPasswordField passwordField;
    private JButton deleteUserButton;

    public DeleteUser() {
        this.deleteUserButton.addActionListener(e -> this.executeOperation());
    }

    @Override
    public void executeOperation() {
        try {
            ClientCheck.checkPassword(new String(passwordField.getPassword()));

            Integer option = Dialogs.showOptionDialog("Certeza que deseja excluir seu cadastro?", this);
            if (option == 0) {
                this.send();
                this.returned();
                ClientApp.menuLogged.closeScreen();
                ClientApp.menuNonLogged.buildScreen();
            }
            this.closeScreen();
        } catch (InvalidFieldException invalidFieldException) {
            System.err.println(invalidFieldException.getMessage());
            Dialogs.showWarningMessage(invalidFieldException.getMessage(), this);
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
            Dialogs.showErrorMessage(ex.getMessage(), this);
        }
    }

    @Override
    public void buildScreen() {
        this.setContentPane(this.deleteUserPanel);
        this.setTitle("Deletar Usário");
        this.setVisible(true);
        this.setSize(350, 300);
    }

    @Override
    public void closeScreen() {
        this.setVisible(false);
    }

    private void send() {
        DeleteUserDataClientToServer deleteUserDataClientToServer =
                new DeleteUserDataClientToServer(ClientSection.getId(), ClientSection.getToken(), new String(passwordField.getPassword()));

        ClientAppSocket.sendMessage(deleteUserDataClientToServer);
    }

    private void returned() throws IOException {
        CommonDataServerToClient commonDataServerToClient = (CommonDataServerToClient) ClientAppSocket.receiveMessage(CommonDataServerToClient.class);

        if (!Objects.equals(commonDataServerToClient.getStatus().trim(), Status.OK)) {
            throw new ServerFailureException(commonDataServerToClient.getStatus());
        }
    }
}
