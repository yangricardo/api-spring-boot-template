package com.github.yangricardo.api_spring_boot.modules.users.model;

import com.github.yangricardo.api_spring_boot.shared.modules.models.BaseModel;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name = "roles")
@Builder
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class Role extends BaseModel implements GrantedAuthority {

  private String role;

  @ManyToMany(mappedBy = "roles")
  private Collection<User> users;

  @Override
  public String getAuthority() {
    return this.role;
  }
}
