package org.utfpr.client.gui.usecase;

import org.utfpr.client.auth.ClientSection;
import org.utfpr.client.exception.ServerFailureException;
import org.utfpr.client.exception.UnauthenticatedException;
import org.utfpr.client.infra.ClientAppSocket;
import org.utfpr.common.dto.auth.logout.LogoutDataClientToServer;
import org.utfpr.common.dto.common.CommonDataServerToClient;
import org.utfpr.common.gui.Dialogs;
import org.utfpr.common.util.Status;

import java.io.IOException;
import java.util.Objects;

public class Logout {
    public void buildScreen() {
        try {
            Integer option = Dialogs.showOptionDialog("Certeza que deseja fazer Logout?");
            if (option == 0) {
                this.send();
                Dialogs.showInfoMessage("Logout feito com Sucesso!");
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
            Dialogs.showErrorMessage(e.getMessage());
        }
    }

    private void send() throws IOException {
        if (ClientSection.getId() == null || ClientSection.getToken() == null) {
            throw new UnauthenticatedException("Não é possível fazer Logout, você não está Logado.");
        }
        LogoutDataClientToServer logoutData = new LogoutDataClientToServer(ClientSection.getId(), ClientSection.getToken());
        ClientAppSocket.sendMessage(logoutData);
        this.returned();
    }

    private void returned() throws IOException {
        CommonDataServerToClient commonDataServerToClient = (CommonDataServerToClient) ClientAppSocket.receiveMessage(CommonDataServerToClient.class);

        if (!Objects.equals(commonDataServerToClient.getStatus().trim(), Status.OK)) {
            throw new ServerFailureException(commonDataServerToClient.getStatus());
        }

        ClientSection.setId(null);
        ClientSection.setName(null);
        ClientSection.setToken(null);
    }
}
