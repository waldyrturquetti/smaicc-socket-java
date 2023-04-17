package org.utfpr.server.dto;

public abstract class Data {

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
