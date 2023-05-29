package org.utfpr.server.domain.usecase.incident;

import org.utfpr.common.dto.incident.getIncidents.GetIncidentsDataClientToServer;
import org.utfpr.common.dto.incident.getIncidents.GetIncidentsDataServerToClient;
import org.utfpr.common.dto.incident.getIncidents.IncidentData;
import org.utfpr.common.util.Convert;
import org.utfpr.server.domain.entities.Incident;
import org.utfpr.server.domain.repository.IncidentRepositoryDAO;
import org.utfpr.server.domain.usecase.UseCase;
import org.utfpr.server.exception.DbException;
import org.utfpr.server.util.Check;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;

public class GetIncidents implements UseCase {

    private final IncidentRepositoryDAO incidentRepositoryDAO;

    public GetIncidents() {
        try {
            this.incidentRepositoryDAO = new IncidentRepositoryDAO();
        } catch (SQLException exception) {
            throw new DbException(exception.getMessage());
        }
    }

    @Override
    public HashMap<String, Object> executeOperation(HashMap<String, Object> json) {
        GetIncidentsDataClientToServer getIncidentsDataClientToServer =
                (GetIncidentsDataClientToServer) Convert.convertHashMapToData(json, GetIncidentsDataClientToServer.class);

        Check.checkDate(getIncidentsDataClientToServer.getDate());
        Check.checkState(getIncidentsDataClientToServer.getState());
        Check.checkCity(getIncidentsDataClientToServer.getCity());

        List<Incident> incidentList = this.incidentRepositoryDAO.getIncidentsByDateAndStateAndCity(
                LocalDate.parse(getIncidentsDataClientToServer.getDate()), getIncidentsDataClientToServer.getState(), getIncidentsDataClientToServer.getCity());

        System.out.println(incidentList);

        List<IncidentData> incidentDataList = incidentList.stream()
                                                    .sorted(Comparator.comparing(Incident::getHour).reversed())
                                                    .map(Incident::convertToData)
                                                .toList();

        return Convert.convertDataToHashMap(new GetIncidentsDataServerToClient(incidentDataList));
    }
}
