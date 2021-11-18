package br.pucrio.les.esg_token_backend.configurations.infra.keycloak;

import java.util.List;

import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.idm.GroupRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class GroupService {

    private String realm;

    @Autowired
    private Keycloak keycloak;

    @Autowired
    public GroupService(@Value("${keycloak.realm}") String realm) {
        this.realm = realm;
    }

    public void create(String name) {
        GroupRepresentation group = new GroupRepresentation();
        group.setName(name);
        keycloak.realm(realm).groups().add(group);
    }

    public List<GroupRepresentation> findAll() {
        return keycloak.realm(realm).groups().groups();
    }

}
