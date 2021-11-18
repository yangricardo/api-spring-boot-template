package br.pucrio.les.esg_token_backend.resources.auth.model;

import lombok.Data;

@Data
public class LoginDTO {

    private String username;
    private String password;

    public LoginDTO() {
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}