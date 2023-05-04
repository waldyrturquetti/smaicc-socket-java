package org.utfpr.common.dto.auth.login;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.utfpr.common.dto.DataServerToClient;

public class LoginDataServerToClient extends DataServerToClient {

    private String token;

    private Integer id;

    @JsonProperty("nome")
    private String name;

    public LoginDataServerToClient() {
        super();
    }

    public LoginDataServerToClient(Integer operation, String status, String token, Integer id, String name) {
        super(operation, status);
        this.token = token;
        this.id = id;
        this.name = name;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
