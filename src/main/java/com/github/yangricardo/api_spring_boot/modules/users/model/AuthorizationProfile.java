package com.github.yangricardo.api_spring_boot.modules.users.model;

import com.github.yangricardo.api_spring_boot.shared.modules.models.BaseModel;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

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
