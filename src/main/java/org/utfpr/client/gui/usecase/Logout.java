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
        Integer option = Dialogs.showOptionDialog("Certeza que deseja fazer Logout?");
        if (option == 0) {
            this.send();
        }
    }

    private void send() {
        try {
            if (ClientSection.getId() == null || ClientSection.getToken() == null) {
                throw new UnauthenticatedException("Não é possível fazer Logout, você não está Logado.");
            }
            LogoutDataClientToServer logoutData = new LogoutDataClientToServer(ClientSection.getId(), ClientSection.getToken());
            ClientAppSocket.sendMessage(logoutData);
            this.returned();
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
            Dialogs.showErrorMessage(ex.getMessage());
        }
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
