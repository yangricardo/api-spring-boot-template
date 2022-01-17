package com.github.yangricardo.api_spring_boot.shared.modules.models;

import java.util.Date;
import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class BaseDTO {
  private Long id;
  private UUID uuid;
  private Date createdAt;
  private Date updatedAt;
}
