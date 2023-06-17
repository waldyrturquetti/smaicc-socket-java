package org.utfpr.client.gui.usecase.user;

import org.utfpr.client.exception.InvalidFieldException;
import org.utfpr.client.exception.ServerFailureException;
import org.utfpr.client.gui.usecase.UseCaseGui;
import org.utfpr.client.gui.usecase.auth.Login;
import org.utfpr.client.infra.ClientAppSocket;
import org.utfpr.client.util.ClientCheck;
import org.utfpr.common.gui.Dialogs;
import org.utfpr.common.util.Hash;
import org.utfpr.common.dto.common.CommonDataServerToClient;
import org.utfpr.common.dto.user.createUser.CreateUserDataClientToServer;
import org.utfpr.common.util.Status;

import javax.swing.*;
import java.io.IOException;
import java.util.Objects;

public class CreateUser extends JFrame implements UseCaseGui {
    private JTextField nameTextField;
    private JTextField emailTextField;
    private JPasswordField passwordField;
    private JButton registerButton;
    private JButton backButton;
    private JPanel createUserPanel;
    private static Boolean isRegisterCompleted = false;

    public CreateUser() {
        this.backButton.addActionListener(e -> this.closeScreen());
        this.registerButton.addActionListener(e -> this.executeOperation());
    }

    @Override
    public void buildScreen() {
        this.setContentPane(this.createUserPanel);
        this.setTitle("Criar Usário");
        this.setVisible(true);
        this.setSize(350, 300);
        isRegisterCompleted = false;
    }

    @Override
    public void closeScreen() {
        if (isRegisterCompleted) {
            Dialogs.showInfoMessage("Cadastro de Usuário feito com Sucesso!", this);
            new Login().buildScreen();
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
        ClientCheck.checkName(this.nameTextField.getText());
        ClientCheck.checkEmail(emailTextField.getText());
        ClientCheck.checkPassword(new String(passwordField.getPassword()));

        CreateUserDataClientToServer createUserData = new CreateUserDataClientToServer(
                this.nameTextField.getText(), this.emailTextField.getText(),
                Hash.encrypt(new String(this.passwordField.getPassword()))
        );
        ClientAppSocket.sendMessage(createUserData);
    }

    private void returned() throws IOException {
        CommonDataServerToClient commonDataServerToClient = (CommonDataServerToClient) ClientAppSocket.receiveMessage(CommonDataServerToClient.class);

        if (!Objects.equals(commonDataServerToClient.getStatus().trim(), Status.OK)) {
            throw new ServerFailureException(commonDataServerToClient.getStatus());
        }

        isRegisterCompleted = true;
    }
}
