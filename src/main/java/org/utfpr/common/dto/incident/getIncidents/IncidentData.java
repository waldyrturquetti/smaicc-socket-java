package org.utfpr.common.dto.incident.getIncidents;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.utfpr.client.util.ComboBoxValues;

public class IncidentData {

    @JsonProperty("id_incidente")
    private Integer incidentId;

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

    public IncidentData(Integer incidentId, String date, String hour, String state, String city, String neighborhood, String street, Integer incidentTypeValue) {
        this.incidentId = incidentId;
        this.date = date;
        this.hour = hour;
        this.state = state;
        this.city = city;
        this.neighborhood = neighborhood;
        this.street = street;
        this.incidentTypeValue = incidentTypeValue;
    }

    public Integer getIncidentId() {
        return incidentId;
    }

    public void setIncidentId(Integer incidentId) {
        this.incidentId = incidentId;
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

    public Object[] convertToObjectArray() {
        return new Object[] {this.date, this.hour, this.state, this.city, this.neighborhood, this.street, ComboBoxValues.getTypeIncidents()[this.incidentTypeValue]};
    }
}
