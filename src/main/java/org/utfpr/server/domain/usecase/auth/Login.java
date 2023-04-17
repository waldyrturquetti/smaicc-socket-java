package org.utfpr.server.domain.usecase.auth;

import org.utfpr.server.domain.repository.UserRepositoryDAO;
import org.utfpr.server.domain.usecase.UseCase;
import org.utfpr.server.dto.auth.login.LoginDataReceived;

import java.sql.SQLException;
import java.util.HashMap;

public class Login implements UseCase {

    private UserRepositoryDAO userRepositoryDAO;

    public Login() throws SQLException {
        userRepositoryDAO = new UserRepositoryDAO();
    }

    @Override
    public HashMap<String, Object> executeOperation(HashMap<String, Object> json) {
        return null;
    }

    private LoginDataReceived convertHashMapToData() {
        return null;
    }

    private HashMap<String, Object> convertDataToHashMap(LoginDataReceived data) {
        return null;
    }
}
