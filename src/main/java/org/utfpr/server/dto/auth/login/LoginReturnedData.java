package org.utfpr.server.dto.auth.login;

public class LoginReturnedData {

    private Integer operation;

    private String status;

    private String token;

    private Integer id;

    public LoginReturnedData() {}

    public LoginReturnedData(Integer operation, String status, String token, Integer id) {
        this.operation = operation;
        this.status = status;
        this.token = token;
        this.id = id;
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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
