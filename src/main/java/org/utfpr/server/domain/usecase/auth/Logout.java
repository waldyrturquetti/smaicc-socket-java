package org.utfpr.server.domain.usecase.auth;

import org.utfpr.server.auth.ServerSection;
import org.utfpr.server.domain.usecase.UseCase;
import org.utfpr.common.dto.auth.logout.LogoutDataClientToServer;
import org.utfpr.common.dto.common.CommonDataServerToClient;
import org.utfpr.common.util.Convert;
import org.utfpr.common.util.Operation;
import org.utfpr.common.util.Status;

import java.util.HashMap;

public class Logout implements UseCase {

    public Logout() {}

    @Override
    public HashMap<String, Object> executeOperation(HashMap<String, Object> json) {
        LogoutDataClientToServer logoutDataClientToServer = (LogoutDataClientToServer) Convert.convertHashMapToData(json, new LogoutDataClientToServer());
        ServerSection.removeToken(logoutDataClientToServer.getId());
        CommonDataServerToClient commonDataServerToClient = new CommonDataServerToClient(Operation.LOGOUT, Status.OK);
        return Convert.convertDataToHashMap(commonDataServerToClient);
    }
}
