package org.utfpr.common.dto.incident.deleteIncident;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.utfpr.common.dto.Data;
import org.utfpr.common.util.Operation;

public class DeleteIncidentDataClientToServer extends Data {
    @JsonProperty("id")
    private Integer userId;
    @JsonProperty("id_incidente")
    private Integer incidentId;
    private String token;

    public DeleteIncidentDataClientToServer() {}

    public DeleteIncidentDataClientToServer(Integer userId, Integer incidentId, String token) {
        super(Operation.REMOVE_INCIDENT_REPORTED_BY_THE_USER);
        this.userId = userId;
        this.incidentId = incidentId;
        this.token = token;
    }

    public DeleteIncidentDataClientToServer(Integer operation, Integer userId, Integer incidentId, String token) {
        super(operation);
        this.userId = userId;
        this.incidentId = incidentId;
        this.token = token;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getIncidentId() {
        return incidentId;
    }

    public void setIncidentId(Integer incidentId) {
        this.incidentId = incidentId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
