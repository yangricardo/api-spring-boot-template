package br.pucrio.les.esg_token_backend.resources.users.model;

import java.util.Collection;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import br.pucrio.les.esg_token_backend.configurations.base.models.BaseModel;

@Entity
@Table(name = "users")
public class User extends BaseModel implements UserDetails {

    private String username;

    private String email;

    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<AuthorizationProfile> authorizationProfiles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorizationProfiles;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public Set<AuthorizationProfile> getAuthorizationProfiles() {
        return this.authorizationProfiles;
    }

    public void setAuthorizationProfiles(Set<AuthorizationProfile> authorizationProfiles) {
        this.authorizationProfiles = authorizationProfiles;
    }
}
