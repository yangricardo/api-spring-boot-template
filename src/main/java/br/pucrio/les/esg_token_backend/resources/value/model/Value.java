package br.pucrio.les.esg_token_backend.resources.value.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import br.pucrio.les.esg_token_backend.configurations.base.models.BaseModel;

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