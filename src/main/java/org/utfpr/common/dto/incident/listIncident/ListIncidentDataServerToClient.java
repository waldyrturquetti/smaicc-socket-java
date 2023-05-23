package org.utfpr.common.dto.incident.listIncident;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.utfpr.common.dto.DataServerToClient;
import org.utfpr.common.util.Operation;
import org.utfpr.common.util.Status;

import java.util.List;

public class ListIncidentDataServerToClient extends DataServerToClient {

    @JsonProperty("incidentes")
    private List<IncidentData> incidentDataList;

    public ListIncidentDataServerToClient(List<IncidentData> incidentDataList) {
        super(Operation.LIST_OF_INCIDENTS, Status.OK);
        this.incidentDataList = incidentDataList;
    }

    public ListIncidentDataServerToClient(Integer operation, String status, List<IncidentData> incidentDataList) {
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
