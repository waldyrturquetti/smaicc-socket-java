package org.utfpr.server.dto.auth.login;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.utfpr.server.dto.DataReturned;

public class LoginDataReturned extends DataReturned {

    private String token;

    private Integer id;

    @JsonProperty("nome")
    private String name;

    public LoginDataReturned() {
        super();
    }

    public LoginDataReturned(Integer operation,String status, String token, Integer id, String name) {
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
