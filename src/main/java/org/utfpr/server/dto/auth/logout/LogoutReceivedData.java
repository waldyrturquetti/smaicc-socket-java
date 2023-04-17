package org.utfpr.server.dto.auth.logout;

public class LogoutReceivedData {

    private String token;
    private Integer id;
    private String operation;

    public LogoutReceivedData() {}

    public LogoutReceivedData(String token, Integer id, String operation) {
        this.token = token;
        this.id = id;
        this.operation = operation;
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

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }
}
