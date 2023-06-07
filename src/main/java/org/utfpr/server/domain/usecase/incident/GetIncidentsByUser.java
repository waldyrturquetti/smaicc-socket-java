package org.utfpr.server.domain.usecase.incident;

import org.utfpr.common.dto.incident.IncidentData;
import org.utfpr.common.dto.incident.getIncidentsByUser.GetIncidentsByUserDataClientToServer;
import org.utfpr.common.dto.incident.getIncidentsByUser.GetIncidentsByUserDataServerToClient;
import org.utfpr.common.util.Convert;
import org.utfpr.server.domain.entities.Incident;
import org.utfpr.server.domain.repository.IncidentRepositoryDAO;
import org.utfpr.server.domain.usecase.UseCase;
import org.utfpr.server.exception.DbException;

import java.sql.SQLException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class GetIncidentsByUser implements UseCase {

    private final IncidentRepositoryDAO incidentRepositoryDAO;

    public GetIncidentsByUser() {
        try {
            this.incidentRepositoryDAO = new IncidentRepositoryDAO();
        } catch (SQLException exception) {
            throw new DbException(exception.getMessage());
        }
    }

    @Override
    public HashMap<String, Object> executeOperation(HashMap<String, Object> json) {
        GetIncidentsByUserDataClientToServer getIncidentsByUserDataClientToServer =
                (GetIncidentsByUserDataClientToServer) Convert.convertHashMapToData(json, GetIncidentsByUserDataClientToServer.class);

        List<Incident> incidentList = this.incidentRepositoryDAO.getIncidentsByUserId(getIncidentsByUserDataClientToServer.getUserId());

        List<IncidentData> incidentDataList = incidentList.stream()
                .sorted(Comparator.comparing(Incident::getHour).reversed())
                .map(Incident::convertToData)
                .toList();

        return Convert.convertDataToHashMap(new GetIncidentsByUserDataServerToClient(incidentDataList));
    }
}
