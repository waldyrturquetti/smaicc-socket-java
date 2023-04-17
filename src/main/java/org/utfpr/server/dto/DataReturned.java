package org.utfpr.server.dto;

public abstract class DataReturned extends Data {

    private String status;

    public DataReturned() {
        super();
    }

    public DataReturned(Integer operation, String status) {
        super(operation);
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
