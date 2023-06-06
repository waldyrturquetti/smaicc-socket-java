package org.utfpr.common.dto.incident.getIncidentsByUser;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.utfpr.common.dto.DataServerToClient;
import org.utfpr.common.dto.incident.IncidentData;
import org.utfpr.common.util.Operation;
import org.utfpr.common.util.Status;

import java.util.List;

public class GetIncidentsByUserDataServerToClient extends DataServerToClient {

    @JsonProperty("incidentes")
    private List<IncidentData> incidentDataList;

    public GetIncidentsByUserDataServerToClient() {}

    public GetIncidentsByUserDataServerToClient(List<IncidentData> incidentDataList) {
        super(Operation.LIST_OF_INCIDENTS_REPORTED_BY_THE_USER, Status.OK);
        this.incidentDataList = incidentDataList;
    }

    public GetIncidentsByUserDataServerToClient(Integer operation, String status, List<IncidentData> incidentDataList) {
        super(operation, status);
        this.incidentDataList = incidentDataList;
    }

    public List<IncidentData> getIncidentDataList() {
        return incidentDataList;
    }

    public void setIncidentDataList(List<IncidentData> incidentDataList) {
        this.incidentDataList = incidentDataList;
    }
}
