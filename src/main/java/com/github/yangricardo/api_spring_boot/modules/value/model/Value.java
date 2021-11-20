package com.github.yangricardo.api_spring_boot.modules.value.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.github.yangricardo.api_spring_boot.shared.modules.models.BaseModel;

@Entity
@Table(name = "value")
public class Value extends BaseModel {
    @Column
    @NotEmpty(message = "Can not be empty")
    @NotBlank(message = "Can not be blank")
    @Size(min = 3, max = 255)
    private String value;

    public Value() {
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
