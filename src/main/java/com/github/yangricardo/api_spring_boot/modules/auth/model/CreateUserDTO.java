package com.github.yangricardo.api_spring_boot.modules.auth.model;

public class CreateUserDTO {
    private String username;
    private String email;
    private String password;

    public CreateUserDTO() {
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
