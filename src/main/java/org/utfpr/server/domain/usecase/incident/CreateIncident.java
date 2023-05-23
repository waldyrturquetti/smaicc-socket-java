package org.utfpr.server.domain.usecase.incident;

import org.utfpr.common.dto.common.CommonDataServerToClient;
import org.utfpr.common.dto.incident.createIncident.CreateIncidentDataClientToServer;
import org.utfpr.common.util.Convert;
import org.utfpr.common.util.Operation;
import org.utfpr.common.util.Status;
import org.utfpr.server.domain.repository.IncidentRepositoryDAO;
import org.utfpr.server.domain.usecase.UseCase;
import org.utfpr.server.exception.DbException;
import org.utfpr.server.util.Check;

import java.sql.SQLException;
import java.util.HashMap;

public class CreateIncident implements UseCase {

    private final IncidentRepositoryDAO incidentRepositoryDAO;

    public CreateIncident() {
        try {
            this.incidentRepositoryDAO = new IncidentRepositoryDAO();
        } catch (SQLException exception) {
            throw new DbException(exception.getMessage());
        }
    }

    @Override
    public HashMap<String, Object> executeOperation(HashMap<String, Object> json) {
        CreateIncidentDataClientToServer createIncidentDataClientToServer =
                (CreateIncidentDataClientToServer) Convert.convertHashMapToData(json, CreateIncidentDataClientToServer.class);

        Check.checkDate(createIncidentDataClientToServer.getDate());
        Check.checkHour(createIncidentDataClientToServer.getHour());
        Check.checkState(createIncidentDataClientToServer.getState());
        Check.checkCity(createIncidentDataClientToServer.getCity());
        Check.checkStreet(createIncidentDataClientToServer.getStreet());

        this.incidentRepositoryDAO.createIncident(createIncidentDataClientToServer.convertToModel());
        return Convert.convertDataToHashMap(new CommonDataServerToClient(Operation.REPORT_INCIDENTS, Status.OK));
    }
}
