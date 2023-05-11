package org.utfpr.server.domain.usecase.user;

import org.utfpr.server.domain.repository.UserRepositoryDAO;
import org.utfpr.server.domain.usecase.UseCase;
import org.utfpr.common.dto.common.CommonDataServerToClient;
import org.utfpr.common.dto.user.createUser.CreateUserDataClientToServer;
import org.utfpr.server.exception.AlreadyExistException;
import org.utfpr.server.exception.DbException;
import org.utfpr.server.util.Check;
import org.utfpr.common.util.Convert;
import org.utfpr.common.util.Operation;
import org.utfpr.common.util.Status;

import java.sql.SQLException;
import java.util.HashMap;

public class CreateUser implements UseCase {

    private final UserRepositoryDAO userRepositoryDAO;

    public CreateUser() {
        try {
            this.userRepositoryDAO = new UserRepositoryDAO();
        } catch (SQLException exception) {
            throw new DbException(exception.getMessage());
        }
    }

    @Override
    public HashMap<String, Object> executeOperation(HashMap<String, Object> json) {
        CreateUserDataClientToServer createUserDataClientToServer =
                (CreateUserDataClientToServer) Convert.convertHashMapToData(json, CreateUserDataClientToServer.class);

        Check.checkName(createUserDataClientToServer.getName());
        Check.checkEmail(createUserDataClientToServer.getEmail());
        Check.checkPassword(createUserDataClientToServer.getPassword());

        if (userRepositoryDAO.existsUserByEmail(createUserDataClientToServer.getEmail())) {
            throw new AlreadyExistException("Email já existente.");
        }

        this.userRepositoryDAO.createUser(createUserDataClientToServer.convertToUser());
        return Convert.convertDataToHashMap(new CommonDataServerToClient(Operation.USER_REGISTRATION, Status.OK));
    }
}
