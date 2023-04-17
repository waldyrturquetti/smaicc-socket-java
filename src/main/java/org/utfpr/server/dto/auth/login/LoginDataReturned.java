package org.utfpr.server.dto.auth.login;

import org.utfpr.server.dto.DataReturned;

public class LoginDataReturned extends DataReturned {

    private String token;

    private Integer id;

    public LoginDataReturned() {
        super();
    }

    public LoginDataReturned(Integer operation,String status, String token, Integer id) {
        super(operation, status);
        this.token = token;
        this.id = id;
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
