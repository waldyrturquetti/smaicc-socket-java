package org.utfpr.server.domain.usecase.incident;

import org.utfpr.common.dto.incident.deleteIncident.DeleteIncidentDataClientToServer;
import org.utfpr.common.util.Convert;
import org.utfpr.server.domain.repository.IncidentRepositoryDAO;
import org.utfpr.server.domain.usecase.UseCase;
import org.utfpr.server.exception.DbException;

import java.sql.SQLException;
import java.util.HashMap;

public class DeleteIncident implements UseCase {

    private final IncidentRepositoryDAO incidentRepositoryDAO;

    public DeleteIncident() {
        try {
            this.incidentRepositoryDAO = new IncidentRepositoryDAO();
        } catch (SQLException exception) {
            throw new DbException(exception.getMessage());
        }
    }

    @Override
    public HashMap<String, Object> executeOperation(HashMap<String, Object> json) {
        DeleteIncidentDataClientToServer deleteIncidentDataClientToServer =
                (DeleteIncidentDataClientToServer) Convert.convertHashMapToData(json, DeleteIncidentDataClientToServer.class);

        return null;
    }
}
