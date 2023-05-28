package org.utfpr.client.gui.usecase.user;

import org.utfpr.client.auth.ClientSection;
import org.utfpr.client.exception.EmptyFieldException;
import org.utfpr.client.exception.ServerFailureException;
import org.utfpr.client.gui.usecase.auth.Logout;
import org.utfpr.client.infra.ClientAppSocket;
import org.utfpr.common.dto.common.CommonDataServerToClient;
import org.utfpr.common.dto.user.updateUser.UpdateUserDataClientToServer;
import org.utfpr.common.gui.Dialogs;
import org.utfpr.common.util.Hash;
import org.utfpr.common.util.Status;

import javax.swing.*;
import java.io.IOException;
import java.util.Objects;

public class UpdateUser extends JFrame {
    private JTextField nameField;
    private JTextField emailField;
    private JPasswordField passwordField;
    private JButton updateButton;
    private JPanel updateUserPanel;

    public UpdateUser() {
        updateButton.addActionListener(e -> {
            try {
                if (nameField.getText().isBlank() || emailField.getText().isBlank()
                        || (new String(passwordField.getPassword()).isBlank())) {
                    throw new EmptyFieldException();
                }

                UpdateUserDataClientToServer updateUserDataClientToServer =
                        new UpdateUserDataClientToServer(ClientSection.getId(), ClientSection.getToken(),
                                nameField.getText(), emailField.getText(), Hash.encrypt(new String(passwordField.getPassword())));

                ClientAppSocket.sendMessage(updateUserDataClientToServer);
                this.returned();
                Dialogs.showInfoMessage("Usuário Atualizado com Sucesso!!");
                this.setVisible(false);

                Logout logout = new Logout();

            } catch (Exception ex) {
                System.err.println(ex.getMessage());
                Dialogs.showErrorMessage(ex.getMessage(), this);
            }
        });
    }

    public void buildScreen() {
        this.setContentPane(this.updateUserPanel);
        this.setTitle("Criar Usário");
        this.setVisible(true);
        this.setSize(350, 300);
    }

    private void returned() throws IOException {
        CommonDataServerToClient commonDataServerToClient = (CommonDataServerToClient) ClientAppSocket.receiveMessage(CommonDataServerToClient.class);

        if (!Objects.equals(commonDataServerToClient.getStatus().trim(), Status.OK)) {
            throw new ServerFailureException(commonDataServerToClient.getStatus());
        }
    }
}
