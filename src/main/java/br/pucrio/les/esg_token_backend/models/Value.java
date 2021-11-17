package br.pucrio.les.esg_token_backend.models;

import javax.persistence.*;

@Entity
@Table(name = "value")
public class Value {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String value;

    public Value() {
    }

    public Value(String value) {
        this.value = value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public Long getId() {
        return id;
    }
}
