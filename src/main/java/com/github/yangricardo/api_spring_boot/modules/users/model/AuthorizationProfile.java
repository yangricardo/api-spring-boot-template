package com.github.yangricardo.api_spring_boot.modules.users.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.github.yangricardo.api_spring_boot.shared.modules.models.BaseModel;

import org.springframework.security.core.GrantedAuthority;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "authorization_profiles")
@Builder
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class AuthorizationProfile extends BaseModel implements GrantedAuthority {

    private String authority;

    @Override
    public String getAuthority() {
        return this.authority;
    }

}
