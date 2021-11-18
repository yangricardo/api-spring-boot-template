package br.pucrio.les.esg_token_backend.resources.auth.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
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