package org.utfpr.server.domain.usecase.user;

import org.utfpr.common.dto.common.CommonDataServerToClient;
import org.utfpr.common.dto.user.deleteUser.DeleteUserDataClientToServer;
import org.utfpr.common.util.Convert;
import org.utfpr.common.util.Operation;
import org.utfpr.common.util.Status;
import org.utfpr.server.domain.repository.UserRepositoryDAO;
import org.utfpr.server.domain.usecase.UseCase;
import org.utfpr.server.exception.DbException;
import org.utfpr.server.exception.NotFoundException;

import java.sql.SQLException;
import java.util.HashMap;

public class DeleteUser implements UseCase {

    private final UserRepositoryDAO userRepositoryDAO;

    public DeleteUser() {
        try {
            this.userRepositoryDAO = new UserRepositoryDAO();
        } catch (SQLException exception) {
            throw new DbException(exception.getMessage());
        }
    }

    @Override
    public HashMap<String, Object> executeOperation(HashMap<String, Object> json) {
        DeleteUserDataClientToServer deleteUserDataClientToServer = (DeleteUserDataClientToServer)
                Convert.convertHashMapToData(json, DeleteUserDataClientToServer.class);

        if (!this.userRepositoryDAO.existsUserById(deleteUserDataClientToServer.getUserId())){
            throw new NotFoundException("Usário não foi deletado, pois não existe no existe no Banco de Dados.");
        }

        this.userRepositoryDAO.deleteUser(deleteUserDataClientToServer.getUserId());
        return Convert.convertDataToHashMap(new CommonDataServerToClient(Operation.REMOVE_USER_REGISTRATION, Status.OK));
    }
}
