package org.utfpr.client.gui.usecase.user;

import org.utfpr.client.exception.EmptyFieldException;
import org.utfpr.client.exception.ServerFailureException;
import org.utfpr.client.gui.usecase.auth.Login;
import org.utfpr.client.infra.ClientAppSocket;
import org.utfpr.common.gui.Dialogs;
import org.utfpr.common.util.Hash;
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
                if (this.nameTextField.getText().isBlank() || this.emailTextField.getText().isBlank()
                        || (new String(this.passwordField.getPassword()).isBlank())) {
                    throw new EmptyFieldException();
                }

                CreateUserDataClientToServer createUserData = new CreateUserDataClientToServer(
                        this.nameTextField.getText(), this.emailTextField.getText(),
                        Hash.encrypt(new String(this.passwordField.getPassword()))
                );
                ClientAppSocket.sendMessage(createUserData);
                this.returned();
                this.setVisible(false);
                Dialogs.showInfoMessage("Cadastro de Usuário feito com Sucesso!", this);
                new Login().buildScreen();
            } catch (Exception ex) {
                System.err.println(ex.getMessage());
                Dialogs.showErrorMessage(ex.getMessage(), this);
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
        CommonDataServerToClient commonDataServerToClient = (CommonDataServerToClient) ClientAppSocket.receiveMessage(CommonDataServerToClient.class);

        if (!Objects.equals(commonDataServerToClient.getStatus().trim(), Status.OK)) {
            throw new ServerFailureException(commonDataServerToClient.getStatus());
        }
    }
}
