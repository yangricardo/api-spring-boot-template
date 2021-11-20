package com.github.yangricardo.api_spring_boot.modules.value.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.github.yangricardo.api_spring_boot.shared.modules.models.BaseModel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "value")
@Builder
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class Value extends BaseModel {
    @Column
    @NotEmpty(message = "Can not be empty")
    @NotBlank(message = "Can not be blank")
    @Size(min = 3, max = 255)
    private String value;
}
