package com.github.yangricardo.api_spring_boot.modules.users.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.github.yangricardo.api_spring_boot.shared.modules.models.BaseModel;

import org.springframework.security.core.GrantedAuthority;

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
