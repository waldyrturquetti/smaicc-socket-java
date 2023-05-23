package org.utfpr.common.dto.incident.createIncident;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.utfpr.common.dto.Data;
import org.utfpr.common.util.Operation;
import org.utfpr.server.domain.entities.Incident;
import org.utfpr.server.domain.entities.IncidentsTypesEnum;

import java.sql.Time;
import java.time.LocalDate;

public class CreateIncidentDataClientToServer extends Data {

    @JsonProperty("id")
    private Integer user_id;

    private String token;

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

    public CreateIncidentDataClientToServer() {}

    public CreateIncidentDataClientToServer(Integer user_id, String token, String date, String hour, String state, String city, String neighborhood, String street, Integer incidentTypeValue) {
        super(Operation.REPORT_INCIDENTS);
        this.user_id = user_id;
        this.token = token;
        this.date = date;
        this.hour = hour;
        this.state = state;
        this.city = city;
        this.neighborhood = neighborhood;
        this.street = street;
        this.incidentTypeValue = incidentTypeValue;
    }

    public CreateIncidentDataClientToServer(Integer operation, Integer user_id, String token, String date, String hour, String state, String city, String neighborhood, String street, Integer incidentTypeValue) {
        super(operation);
        this.user_id = user_id;
        this.token = token;
        this.date = date;
        this.hour = hour;
        this.state = state;
        this.city = city;
        this.neighborhood = neighborhood;
        this.street = street;
        this.incidentTypeValue = incidentTypeValue;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public String getToken() {
        return token;
    }

    public String getDate() {
        return date;
    }

    public String getHour() {
        return hour;
    }

    public String getState() {
        return state;
    }

    public String getCity() {
        return city;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public String getStreet() {
        return street;
    }

    public Integer getIncidentTypeValue() {
        return incidentTypeValue;
    }

    public Incident convertToModel() {
        return new Incident(
                this.user_id,
                LocalDate.parse(this.date),
                Time.valueOf(this.hour),
                this.state,
                this.city,
                this.neighborhood,
                this.street,
                IncidentsTypesEnum.getEnum(this.incidentTypeValue)
        );
    }
}
