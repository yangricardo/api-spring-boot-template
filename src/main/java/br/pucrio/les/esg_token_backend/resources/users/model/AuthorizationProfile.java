package br.pucrio.les.esg_token_backend.resources.users.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;

import br.pucrio.les.esg_token_backend.configurations.base.models.BaseModel;

@Entity
@Table(name = "authorization_profiles")
public class AuthorizationProfile extends BaseModel implements GrantedAuthority {

    private String authority;

    @Override
    public String getAuthority() {
        return this.authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }
}
