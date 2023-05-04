package org.utfpr.common.dto;

public abstract class DataServerToClient extends Data {

    private String status;

    public DataServerToClient() {
        super();
    }

    public DataServerToClient(Integer operation, String status) {
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
