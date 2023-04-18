package org.utfpr.server.domain.usecase.auth;

import org.utfpr.server.domain.repository.UserRepositoryDAO;
import org.utfpr.server.domain.usecase.UseCase;
import org.utfpr.server.dto.auth.login.LoginDataReceived;
import org.utfpr.server.exception.DbException;
import org.utfpr.server.util.Converts;

import java.sql.SQLException;
import java.util.HashMap;

public class Login implements UseCase {

    private UserRepositoryDAO userRepositoryDAO;

    public Login() throws SQLException {
        try {
            userRepositoryDAO = new UserRepositoryDAO();
        } catch (SQLException exception) {
            throw new DbException(exception.getMessage());
        }

    }

    @Override
    public HashMap<String, Object> executeOperation(HashMap<String, Object> json) {
        System.out.println("Json: " + json.toString());
        Converts.convertHashMapToData(json, new LoginDataReceived());
        return null;
    }
}
