package org.utfpr.common.dto.incident.getIncidents;

import com.fasterxml.jackson.annotation.JsonProperty;

public class IncidentData {

    @JsonProperty("id_incidente")
    private Integer incident_id;

    @JsonProperty("data")
    private String date;

    @JsonProperty("hora")
    private String hour;

    @JsonProperty("estado")
    private String state;

    @JsonProperty("cidade")
    private String city;

    @JsonProperty("bairro")
    private String neighborhood;

    @JsonProperty("rua")
    private String street;

    @JsonProperty("tipo_incidente")
    private Integer incidentTypeValue;

    public IncidentData() {}

    public IncidentData(Integer incident_id, String date, String hour, String state, String city, String neighborhood, String street, Integer incidentTypeValue) {
        this.incident_id = incident_id;
        this.date = date;
        this.hour = hour;
        this.state = state;
        this.city = city;
        this.neighborhood = neighborhood;
        this.street = street;
        this.incidentTypeValue = incidentTypeValue;
    }

    public Integer getIncident_id() {
        return incident_id;
    }

    public void setIncident_id(Integer incident_id) {
        this.incident_id = incident_id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
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

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Integer getIncidentTypeValue() {
        return incidentTypeValue;
    }

    public void setIncidentTypeValue(Integer incidentTypeValue) {
        this.incidentTypeValue = incidentTypeValue;
    }
}
