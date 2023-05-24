package org.utfpr.server.domain.entities;

import org.utfpr.common.dto.incident.getIncidents.IncidentData;

import java.time.LocalDate;
import java.time.LocalTime;

public class Incident {

    private Integer id;

    private Integer userId;

    private LocalDate date;

    private LocalTime hour;

    private String state;

    private String city;

    private String neighborhood;

    private String street;

    private IncidentsTypesEnum incidentsTypesEnum;

    public Incident() {}

    public Incident(Integer userId, LocalDate date, LocalTime hour, String state, String city, String neighborhood, String street, IncidentsTypesEnum incidentsTypesEnum) {
        this.userId = userId;
        this.date = date;
        this.hour = hour;
        this.state = state;
        this.city = city;
        this.neighborhood = neighborhood;
        this.street = street;
        this.incidentsTypesEnum = incidentsTypesEnum;
    }

    public Incident(Integer id, Integer userId, LocalDate date, LocalTime hour, String state, String city, String neighborhood, String street, IncidentsTypesEnum incidentsTypesEnum) {
        this.id = id;
        this.userId = userId;
        this.date = date;
        this.hour = hour;
        this.state = state;
        this.city = city;
        this.neighborhood = neighborhood;
        this.street = street;
        this.incidentsTypesEnum = incidentsTypesEnum;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUser_id(Integer user_id) {
        this.userId = user_id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getHour() {
        return hour;
    }

    public void setHour(LocalTime hour) {
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

    public IncidentsTypesEnum getIncidentsTypesEnum() {
        return incidentsTypesEnum;
    }

    public void setIncidentsTypesEnum(IncidentsTypesEnum incidentsTypesEnum) {
        this.incidentsTypesEnum = incidentsTypesEnum;
    }

    public IncidentData convertToData() {
        return new IncidentData(this.id, this.date.toString(), this.hour.toString(), this.state, this.city,
                this.neighborhood, this.street, this.incidentsTypesEnum.getValue());
    }

    @Override
    public String toString() {
        return "Incident{" +
                "id=" + id +
                ", user_id=" + userId +
                ", date=" + date +
                ", hour=" + hour +
                ", state='" + state + '\'' +
                ", city='" + city + '\'' +
                ", neighborhood='" + neighborhood + '\'' +
                ", street='" + street + '\'' +
                ", incidentsTypesEnum=" + incidentsTypesEnum +
                '}';
    }
}
