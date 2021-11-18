package br.pucrio.les.esg_token_backend.controllers;

import java.util.List;

import javax.ws.rs.core.Response;

import org.keycloak.representations.idm.UserRepresentation;
import org.keycloak.representations.idm.GroupRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.pucrio.les.esg_token_backend.configurations.infra.keycloak.CreateUserDTO;
import br.pucrio.les.esg_token_backend.configurations.infra.keycloak.GroupService;
import br.pucrio.les.esg_token_backend.configurations.infra.keycloak.RoleService;
import br.pucrio.les.esg_token_backend.configurations.infra.keycloak.UserService;

@RestController
@RequestMapping("/keycloak")
public class KeycloakController extends BaseController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private GroupService groupService;

    @Autowired
    private UserService usersService;

    @GetMapping("/roles")
    public List<RoleRepresentation> findAllRoles() {
        return roleService.findAll();
    }

    @PostMapping("/roles/{name}")
    public void createRole(@PathVariable("name") String name) {
        roleService.create(name);
    }

    @GetMapping("/groups")
    public List<GroupRepresentation> findAllGroups() {
        return groupService.findAll();
    }

    @PostMapping("/groups/{name}")
    public void createGroup(@PathVariable("name") String name) {
        groupService.create(name);
    }

    @PostMapping("/users")
    public ResponseEntity<?> createUser(@RequestBody CreateUserDTO user) {
        Response response = usersService.create(user);
        UserRepresentation userRepresentation = usersService.findByUsername(user.getUsername()).get(0);
        RoleRepresentation roleUserRepresentation = roleService.findByName("ROLE_USER");
        userRepresentation = usersService.findByUsername(user.getUsername()).get(0);
        usersService.assignRole(userRepresentation.getId(), roleUserRepresentation);
        return ResponseEntity.status(response.getStatus()).body(userRepresentation);
    }

}
