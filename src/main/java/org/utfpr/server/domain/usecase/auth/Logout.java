package org.utfpr.server.domain.usecase.auth;

import org.utfpr.server.auth.Section;
import org.utfpr.server.domain.usecase.UseCase;
import org.utfpr.server.dto.auth.logout.LogoutDataReceived;
import org.utfpr.server.dto.common.CommonDataReturned;
import org.utfpr.server.util.Convert;
import org.utfpr.server.util.Operation;
import org.utfpr.server.util.Status;

import java.util.HashMap;

public class Logout implements UseCase {

    public Logout() {}

    @Override
    public HashMap<String, Object> executeOperation(HashMap<String, Object> json) {
        LogoutDataReceived logoutDataReceived = (LogoutDataReceived) Convert.convertHashMapToData(json, new LogoutDataReceived());
        Section.removeToken(logoutDataReceived.getId());
        CommonDataReturned commonDataReturned = new CommonDataReturned(Operation.LOGOUT, Status.OK);
        return Convert.convertDataToHashMap(commonDataReturned);
    }
}
