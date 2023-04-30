package org.utfpr.server.domain.usecase.auth;

import org.utfpr.server.auth.Section;
import org.utfpr.server.domain.entities.User;
import org.utfpr.server.domain.repository.UserRepositoryDAO;
import org.utfpr.server.domain.usecase.UseCase;
import org.utfpr.server.dto.auth.login.LoginDataReceived;
import org.utfpr.server.dto.auth.login.LoginDataReturned;
import org.utfpr.server.exception.DbException;
import org.utfpr.server.exception.NotFoundException;
import org.utfpr.server.util.Check;
import org.utfpr.server.util.Convert;
import org.utfpr.server.util.Operation;
import org.utfpr.server.util.Status;

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
        LoginDataReceived loginDataReceived = (LoginDataReceived) Convert.convertHashMapToData(json, new LoginDataReceived());
        Check.checkEmail(loginDataReceived.getEmail());
        Check.checkPassword(loginDataReceived.getPassword());

        User user = userRepositoryDAO.getUserByEmailAndPassword(loginDataReceived.getEmail(), loginDataReceived.getPassword());
        if (user == null){
            throw new NotFoundException("Usuario nao encontrado.");
        }

        String token = Section.authenticatingUser(user.getId());
        LoginDataReturned loginDataReturned = new LoginDataReturned(Operation.LOGIN, Status.OK, token, user.getId());

        return Convert.convertDataToHashMap(loginDataReturned);
    }
}
