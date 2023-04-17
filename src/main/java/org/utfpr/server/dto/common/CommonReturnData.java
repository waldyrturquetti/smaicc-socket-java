package org.utfpr.server.dto.common;

public class CommonReturnData {

    private Integer operation;

    private String status;

    public CommonReturnData(Integer operation, String status) {
        this.operation = operation;
        this.status = status;
    }

    public Integer getOperation() {
        return operation;
    }

    public void setOperation(Integer operation) {
        this.operation = operation;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
