package com.github.yangricardo.api_spring_boot.modules.users.model;

import java.util.HashSet;
import java.util.Set;

import com.github.yangricardo.api_spring_boot.shared.modules.models.BaseDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@EqualsAndHashCode(callSuper = true)
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserWithRolesDTO extends BaseDTO {

  private String username;
  @Builder.Default
  private Set<RoleDTO> roles = new HashSet();
}
