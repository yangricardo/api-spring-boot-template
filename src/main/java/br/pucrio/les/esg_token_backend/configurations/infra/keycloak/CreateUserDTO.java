package br.pucrio.les.esg_token_backend.configurations.infra.keycloak;

public class CreateUserDTO {
    private String username;
    private String password;

    public CreateUserDTO() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
