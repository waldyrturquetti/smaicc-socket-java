package org.utfpr.common.dto.incident.getIncidentsByUser;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.utfpr.common.dto.Data;
import org.utfpr.common.util.Operation;

public class GetIncidentsByUserDataClientToServer extends Data {

    @JsonProperty("id")
    private Integer userId;

    private String token;

    public GetIncidentsByUserDataClientToServer(){}

    public GetIncidentsByUserDataClientToServer(Integer userId, String token) {
        super(Operation.LIST_OF_INCIDENTS_REPORTED_BY_THE_USER);
        this.userId = userId;
        this.token = token;
    }

    public GetIncidentsByUserDataClientToServer(Integer operation, Integer userId, String token) {
        super(operation);
        this.userId = userId;
        this.token = token;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
