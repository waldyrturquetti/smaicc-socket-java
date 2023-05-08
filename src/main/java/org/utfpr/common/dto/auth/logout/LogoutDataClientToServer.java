package org.utfpr.common.dto.auth.logout;

import org.utfpr.common.dto.Data;
import org.utfpr.common.util.Operation;

public class LogoutDataClientToServer extends Data {

    private Integer id;
    private String token;

    public LogoutDataClientToServer() {
       super();
    }

    public LogoutDataClientToServer(Integer id, String token) {
        super(Operation.LOGOUT);
        this.id = id;
        this.token = token;
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
