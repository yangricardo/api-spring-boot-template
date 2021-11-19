package tech.yang.api_spring_boot.resources.auth.model;

import lombok.Data;

@Data
public class TokenDTO {

    private String type;
    private String token;

    public TokenDTO() {
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getToken() {
        return token;
    }

    public String getType() {
        return type;
    }

}