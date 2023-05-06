package org.utfpr.server.domain.usecase.auth;

import org.utfpr.server.auth.ServerSection;
import org.utfpr.server.domain.entities.User;
import org.utfpr.server.domain.repository.UserRepositoryDAO;
import org.utfpr.server.domain.usecase.UseCase;
import org.utfpr.common.dto.auth.login.LoginDataClientToServer;
import org.utfpr.common.dto.auth.login.LoginDataServerToClient;
import org.utfpr.server.exception.DbException;
import org.utfpr.server.exception.NotFoundException;
import org.utfpr.server.util.Check;
import org.utfpr.common.util.Convert;
import org.utfpr.common.util.Operation;
import org.utfpr.common.util.Status;

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
        LoginDataClientToServer loginDataClientToServer = (LoginDataClientToServer) Convert.convertHashMapToData(json, new LoginDataClientToServer());
        Check.checkEmail(loginDataClientToServer.getEmail());
        Check.checkPassword(loginDataClientToServer.getPassword());

        User user = userRepositoryDAO.getUserByEmailAndPassword(loginDataClientToServer.getEmail(), loginDataClientToServer.getPassword());
        if (user == null){
            throw new NotFoundException("Usuario nao encontrado ou Email/Senha incorretos.");
        }

        String token = ServerSection.authenticatingUser(user.getId());
        LoginDataServerToClient loginDataServerToClient = new LoginDataServerToClient(Operation.LOGIN, Status.OK, token, user.getId(), user.getName());

        return Convert.convertDataToHashMap(loginDataServerToClient);
    }
}
