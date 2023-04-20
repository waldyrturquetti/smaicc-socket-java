package org.utfpr.server.dto.auth.login;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.utfpr.server.dto.Data;

public class LoginDataReceived extends Data {

    private String email;

    @JsonProperty("senha")
    private String password;

    public LoginDataReceived() {
        super();
    }

    public LoginDataReceived(Integer operation, String email, String password) {
        super(operation);
        this.email = email;
        this.password = password;
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
}
