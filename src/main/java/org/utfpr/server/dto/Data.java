package org.utfpr.server.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class Data {

    @JsonProperty("operacao")
    private Integer operation;

    public Data() {}

    public Data(Integer operation) {
        this.operation = operation;
    }

    public Integer getOperation() {
        return operation;
    }

    public void setOperation(Integer operation) {
        this.operation = operation;
    }
}
