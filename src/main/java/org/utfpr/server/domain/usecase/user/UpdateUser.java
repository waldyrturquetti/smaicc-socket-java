package org.utfpr.server.domain.usecase.user;

import org.utfpr.common.dto.common.CommonDataServerToClient;
import org.utfpr.common.dto.user.updateUser.UpdateUserDataClientToServer;
import org.utfpr.common.util.Convert;
import org.utfpr.common.util.Operation;
import org.utfpr.common.util.Status;
import org.utfpr.server.auth.ServerSection;
import org.utfpr.server.domain.repository.UserRepositoryDAO;
import org.utfpr.server.domain.usecase.UseCase;
import org.utfpr.server.exception.AlreadyExistException;
import org.utfpr.server.exception.DbException;
import org.utfpr.server.util.Check;

import java.sql.SQLException;
import java.util.HashMap;

public class UpdateUser implements UseCase {

    private final UserRepositoryDAO userRepositoryDAO;

    public UpdateUser() {
        try {
            this.userRepositoryDAO = new UserRepositoryDAO();
        } catch (SQLException exception) {
            throw new DbException(exception.getMessage());
        }
    }

    @Override
    public HashMap<String, Object> executeOperation(HashMap<String, Object> json) {
        UpdateUserDataClientToServer updateUserDataClientToServer =
                (UpdateUserDataClientToServer) Convert.convertHashMapToData(json, UpdateUserDataClientToServer.class);

        Check.checkName(updateUserDataClientToServer.getName());
        Check.checkEmail(updateUserDataClientToServer.getEmail());
        Check.checkPassword(updateUserDataClientToServer.getPassword());

        if (userRepositoryDAO.existsUserByThisEmailAndDiffId(updateUserDataClientToServer.getEmail(), updateUserDataClientToServer.getId())) {
            throw new AlreadyExistException("Email já existente por outro usuário.");
        }

        this.userRepositoryDAO.updateUser(updateUserDataClientToServer.convertToUser());
        ServerSection.removeToken(updateUserDataClientToServer.getId());
        return Convert.convertDataToHashMap(new CommonDataServerToClient(Operation.USER_REGISTRATION_UPDATE, Status.OK));
    }
}
