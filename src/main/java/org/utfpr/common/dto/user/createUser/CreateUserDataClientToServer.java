package org.utfpr.common.dto.user.createUser;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.utfpr.common.util.Operation;
import org.utfpr.server.domain.entities.User;
import org.utfpr.common.dto.Data;

public class CreateUserDataClientToServer extends Data {

    @JsonProperty("nome")
    private String name;

    private String email;

    @JsonProperty("senha")
    private String password;

    public CreateUserDataClientToServer() {}

    public CreateUserDataClientToServer(String name, String email, String password) {
        super(Operation.USER_REGISTRATION);
        this.name = name;
        this.password = password;
        this.email = email;
    }

    public CreateUserDataClientToServer(Integer operation, String name, String email, String password) {
        super(operation);
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public User convertToUser() {
        return new User(this.name, this.email, this.password);
    }
}
