package org.utfpr.server.dto.auth.logout;

import org.utfpr.server.dto.Data;

public class LogoutDataReceived extends Data {

    private String token;
    private Integer id;

    public LogoutDataReceived() {
       super();
    }

    public LogoutDataReceived(Integer operation, String token, Integer id) {
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
