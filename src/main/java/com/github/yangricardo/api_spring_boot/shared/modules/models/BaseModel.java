package com.github.yangricardo.api_spring_boot.shared.modules.models;

import java.util.Date;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@MappedSuperclass
@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class BaseModel {

  @Id
  @Column
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "uuid", updatable = false, unique = true, nullable = false)
  private UUID uuid;

  @Column
  protected Date createdAt;

  @Column
  protected Date updatedAt;

  @PrePersist
  public void onPrePersist() {
    if (this.uuid == null) {
      this.setUuid(UUID.randomUUID());
    }
    if (this.createdAt == null) {
      this.createdAt = new Date();
      this.updatedAt = this.createdAt;
    }
  }

  @PreUpdate
  public void onPreUpdate() {
    this.updatedAt = new Date();
  }
}
