package tech.yang.api_spring_boot.resources.users.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;

import tech.yang.api_spring_boot.configurations.base.models.BaseModel;

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
