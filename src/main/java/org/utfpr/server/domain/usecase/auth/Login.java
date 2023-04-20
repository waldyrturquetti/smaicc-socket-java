package org.utfpr.server.domain.usecase.auth;

import org.utfpr.server.domain.repository.UserRepositoryDAO;
import org.utfpr.server.domain.usecase.UseCase;
import org.utfpr.server.dto.auth.login.LoginDataReceived;
import org.utfpr.server.exception.DbException;
import org.utfpr.server.util.Converts;

import java.sql.SQLException;
import java.util.HashMap;

public class Login implements UseCase {

    private final UserRepositoryDAO userRepositoryDAO;

    public Login() {
        try {
            userRepositoryDAO = new UserRepositoryDAO();
        } catch (SQLException exception) {
            throw new DbException(exception.getMessage());
        }
    }

    @Override
    public HashMap<String, Object> executeOperation(HashMap<String, Object> json) {
        LoginDataReceived loginDataReceived = (LoginDataReceived) Converts.convertHashMapToData(json, new LoginDataReceived());

        userRepositoryDAO.getUserByEmailAndPassword(loginDataReceived.getEmail(), loginDataReceived.getPassword());

        return null;
    }
}
