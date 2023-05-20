package org.utfpr.common.dto.user.updateUser;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.utfpr.common.dto.Data;
import org.utfpr.common.util.Operation;
import org.utfpr.server.domain.entities.User;

public class UpdateUserDataClientToServer extends Data {

    private Integer id;

    private String token;

    @JsonProperty("nome")
    private String name;

    private String email;

    @JsonProperty("senha")
    private String password;

    public UpdateUserDataClientToServer() {}

    public UpdateUserDataClientToServer(Integer id, String token, String name, String email, String password) {
        super(Operation.USER_REGISTRATION_UPDATE);
        this.id = id;
        this.token = token;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public UpdateUserDataClientToServer(Integer operation, Integer id, String token, String name, String email, String password) {
        super(operation);
        this.id = id;
        this.token = token;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public User convertToUser() {
        return new User(this.id, this.name, this.email, this.password);
    }
}
