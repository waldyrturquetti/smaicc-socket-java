package org.utfpr.server.dto.user.createUser;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.utfpr.server.domain.entities.User;
import org.utfpr.server.dto.Data;

public class CreateUserDataReceived extends Data {

    @JsonProperty("nome")
    private String name;

    @JsonProperty("senha")
    private String password;

    private String email;

    public CreateUserDataReceived() {}

    public CreateUserDataReceived(Integer operation, String name, String password, String email) {
        super(operation);
        this.name = name;
        this.password = password;
        this.email = email;
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
