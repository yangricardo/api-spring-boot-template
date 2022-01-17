package com.github.yangricardo.api_spring_boot.modules.users.model;

import com.github.yangricardo.api_spring_boot.shared.modules.models.BaseDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO extends BaseDTO {
  private String username;
}
