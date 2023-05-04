package org.utfpr.common.dto.auth.login;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.utfpr.common.dto.Data;
import org.utfpr.common.util.Operation;

public class LoginDataClientToServer extends Data {

    private String email;

    @JsonProperty("senha")
    private String password;

    public LoginDataClientToServer() {
        super();
    }

    public LoginDataClientToServer(String email, String password) {
        super(Operation.LOGIN);
        this.email = email;
        this.password = password;
    }

    public LoginDataClientToServer(Integer operation, String email, String password) {
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
