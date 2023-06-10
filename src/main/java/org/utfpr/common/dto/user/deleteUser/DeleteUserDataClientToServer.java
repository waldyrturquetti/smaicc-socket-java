package org.utfpr.common.dto.user.deleteUser;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.utfpr.common.dto.Data;
import org.utfpr.common.util.Operation;

public class DeleteUserDataClientToServer extends Data {

    @JsonProperty("id")
    private Integer userId;

    private String token;

    @JsonProperty("senha")
    private String password;

    public DeleteUserDataClientToServer() {}

    public DeleteUserDataClientToServer(Integer userId, String token, String password) {
        super(Operation.REMOVE_USER_REGISTRATION);
        this.userId = userId;
        this.token = token;
        this.password = password;
    }

    public DeleteUserDataClientToServer(Integer operation, Integer userId, String token, String password) {
        super(operation);
        this.userId = userId;
        this.token = token;
        this.password = password;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
