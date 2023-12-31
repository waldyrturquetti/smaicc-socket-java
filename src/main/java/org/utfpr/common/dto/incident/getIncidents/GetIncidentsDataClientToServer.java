package org.utfpr.common.dto.incident.getIncidents;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.utfpr.common.dto.Data;
import org.utfpr.common.util.Operation;

public class GetIncidentsDataClientToServer extends Data {

    @JsonProperty("data")
    private String date;

    @JsonProperty("estado")
    private String state;

    @JsonProperty("cidade")
    private String city;

    public GetIncidentsDataClientToServer() {}

    public GetIncidentsDataClientToServer(String date, String state, String city) {
        super(Operation.LIST_OF_INCIDENTS);
        this.date = date;
        this.state = state;
        this.city = city;
    }

    public GetIncidentsDataClientToServer(Integer operation, String date, String state, String city) {
        super(operation);
        this.date = date;
        this.state = state;
        this.city = city;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
