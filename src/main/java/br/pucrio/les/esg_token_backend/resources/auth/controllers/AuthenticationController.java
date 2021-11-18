package br.pucrio.les.esg_token_backend.resources.auth.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.pucrio.les.esg_token_backend.resources.auth.model.LoginDTO;
import br.pucrio.les.esg_token_backend.resources.auth.model.TokenDTO;
import br.pucrio.les.esg_token_backend.resources.auth.services.TokenIssuerService;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenIssuerService tokenIssuerService;

    @PostMapping
    public ResponseEntity<?> auth(@RequestBody @Validated LoginDTO loginDTO) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                loginDTO.getUsername(), loginDTO.getPassword());

        Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        String jwt = tokenIssuerService.generateToken(authentication);
        TokenDTO token = new TokenDTO();
        token.setToken(jwt);
        token.setType("Bearer");
        return ResponseEntity.ok(token);

    }
}
