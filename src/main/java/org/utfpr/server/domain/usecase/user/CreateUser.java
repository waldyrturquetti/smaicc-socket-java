package org.utfpr.server.domain.usecase.user;

import org.utfpr.server.domain.repository.UserRepositoryDAO;
import org.utfpr.server.domain.usecase.UseCase;
import org.utfpr.server.dto.common.CommonDataReturned;
import org.utfpr.server.dto.user.createUser.CreateUserDataReceived;
import org.utfpr.server.exception.AlreadyExistException;
import org.utfpr.server.exception.DbException;
import org.utfpr.server.util.Check;
import org.utfpr.server.util.Convert;
import org.utfpr.server.util.Operation;
import org.utfpr.server.util.Status;

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
        CreateUserDataReceived createUserDataReceived = (CreateUserDataReceived) Convert.convertHashMapToData(json, new CreateUserDataReceived());

        Check.checkName(createUserDataReceived.getName());
        Check.checkEmail(createUserDataReceived.getEmail());
        Check.checkPassword(createUserDataReceived.getPassword());

        if (userRepositoryDAO.existsUserByEmail(createUserDataReceived.getEmail())) {
            throw new AlreadyExistException("Email já existente.");
        }

        this.userRepositoryDAO.createUser(createUserDataReceived.convertToUser());
        return Convert.convertDataToHashMap(new CommonDataReturned(Operation.USER_REGISTRATION, Status.OK));
    }
}
