package br.pucrio.les.esg_token_backend.configurations.infra.keycloak;

import java.util.List;

import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.idm.RoleRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    private String realm;

    @Autowired
    private Keycloak keycloak;

    @Autowired
    public RoleService(@Value("${keycloak.realm}") String realm) {
        this.realm = realm;
    }

    public void create(String name) {
        RoleRepresentation role = new RoleRepresentation();
        role.setName(name);
        keycloak.realm(realm).roles().create(role);
    }

    public List<RoleRepresentation> findAll() {
        return keycloak.realm(realm).roles().list();
    }

    public RoleRepresentation findByName(String roleName) {
        return keycloak.realm(realm).roles().get(roleName).toRepresentation();
    }

}
