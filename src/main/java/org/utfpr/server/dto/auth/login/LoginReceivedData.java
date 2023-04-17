package org.utfpr.server.dto.auth.login;

public class LoginReceivedData {

    private String email;
    private String password;
    private Integer operation;

    public LoginReceivedData() {}

    public LoginReceivedData(String email, String password, Integer operation) {
        this.email = email;
        this.password = password;
        this.operation = operation;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getOperation() {
        return operation;
    }

    public void setOperation(Integer operation) {
        this.operation = operation;
    }
}
