package br.pucrio.les.esg_token_backend.configurations.infra.keycloak;

import java.util.List;

import javax.ws.rs.core.Response;

import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private String realm;

    @Autowired
    private Keycloak keycloak;

    @Autowired
    public UserService(@Value("${keycloak.realm}") String realm) {
        this.realm = realm;
    }

    private UsersResource usersResource() {
        return keycloak.realm(realm).users();
    };

    public List<UserRepresentation> findAll() {
        return usersResource().list();
    }

    public List<UserRepresentation> findByUsername(String username) {
        return usersResource().search(username);
    }

    public UserRepresentation findById(String id) {
        return usersResource().get(id).toRepresentation();
    }

    public void assignToGroup(String id, String groupId) {
        usersResource().get(id).joinGroup(groupId);
    }

    public void assignRole(String id, RoleRepresentation roleRepresentation) {
        usersResource().get(id).roles().realmLevel().add(List.of(roleRepresentation));
    }

    private CredentialRepresentation prepareCredentialRepresentation(String password) {
        CredentialRepresentation cr = new CredentialRepresentation();
        cr.setTemporary(false);
        cr.setType(CredentialRepresentation.PASSWORD);
        cr.setValue(password);
        return cr;
    }

    private UserRepresentation prepareUserRepresentation(CreateUserDTO createUserDTO,
            CredentialRepresentation credentialRepresentation) {
        UserRepresentation userRepresentation = new UserRepresentation();
        userRepresentation.setUsername(createUserDTO.getUsername());
        userRepresentation.setCredentials(List.of(credentialRepresentation));
        userRepresentation.setEnabled(true);
        return userRepresentation;
    }

    public Response create(CreateUserDTO createUserDTO) {
        CredentialRepresentation credentialRepresentation = prepareCredentialRepresentation(
                createUserDTO.getPassword());
        UserRepresentation user = prepareUserRepresentation(createUserDTO, credentialRepresentation);
        return usersResource().create(user);
    }
}
