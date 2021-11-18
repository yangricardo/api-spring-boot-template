package br.pucrio.les.esg_token_backend.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "esg_transfers")
public class EsgTransfer extends BaseModel {
    @Column
    private String project;

    @Column
    private String description;

    @Column
    private String recipient;

    @Column
    private String tokenQuantity;

    @Column
    private String documentName;

    @Column
    private String documentHash;

    public EsgTransfer() {
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDocumentHash(String documentHash) {
        this.documentHash = documentHash;
    }

    public String getDocumentHash() {
        return documentHash;
    }

    public void setDocumentName(String documentName) {
        this.documentName = documentName;
    }

    public String getDocumentName() {
        return documentName;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getTokenQuantity() {
        return tokenQuantity;
    }

    public void setTokenQuantity(String tokenQuantity) {
        this.tokenQuantity = tokenQuantity;
    }

}
