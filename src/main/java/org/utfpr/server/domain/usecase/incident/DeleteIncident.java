package org.utfpr.server.domain.usecase.incident;

import org.utfpr.common.dto.common.CommonDataServerToClient;
import org.utfpr.common.dto.incident.deleteIncident.DeleteIncidentDataClientToServer;
import org.utfpr.common.util.Convert;
import org.utfpr.common.util.Operation;
import org.utfpr.common.util.Status;
import org.utfpr.server.domain.repository.IncidentRepositoryDAO;
import org.utfpr.server.domain.usecase.UseCase;
import org.utfpr.server.exception.AlreadyExistException;
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

        if (!this.incidentRepositoryDAO.existsIncidentsById(deleteIncidentDataClientToServer.getIncidentId())) {
            throw new AlreadyExistException("Incidente não existe ou já foi excluído.");
        }

        this.incidentRepositoryDAO.deleteIncidentById(deleteIncidentDataClientToServer.getIncidentId());
        return Convert.convertDataToHashMap(new CommonDataServerToClient(Operation.REMOVE_INCIDENT_REPORTED_BY_THE_USER, Status.OK));
    }
}
