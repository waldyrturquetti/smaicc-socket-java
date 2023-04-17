package org.utfpr.server.domain.usecase.auth;

import org.utfpr.server.dto.auth.login.LoginReceivedData;

import java.util.HashMap;

public class LoginImpl implements Login {

    @Override
    public HashMap<String, Object> executeOperation(HashMap<String, Object> json) {
        return null;
    }

    @Override
    public LoginReceivedData convertHashMapToData() {
        return null;
    }

    @Override
    public HashMap<String, Object> convertDataToHashMap(LoginReceivedData data) {
        return null;
    }
}
