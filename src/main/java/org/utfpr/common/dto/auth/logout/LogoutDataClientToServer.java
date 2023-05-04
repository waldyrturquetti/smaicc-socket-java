package org.utfpr.common.dto.auth.logout;

import org.utfpr.common.dto.Data;

public class LogoutDataClientToServer extends Data {

    private String token;
    private Integer id;

    public LogoutDataClientToServer() {
       super();
    }

    public LogoutDataClientToServer(Integer operation, String token, Integer id) {
        super(operation);
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
