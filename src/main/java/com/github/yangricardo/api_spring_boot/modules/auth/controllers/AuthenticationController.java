package com.github.yangricardo.api_spring_boot.modules.auth.controllers;

import com.github.yangricardo.api_spring_boot.modules.auth.model.CreateUserDTO;
import com.github.yangricardo.api_spring_boot.modules.auth.model.LoginDTO;
import com.github.yangricardo.api_spring_boot.modules.auth.model.TokenDTO;
import com.github.yangricardo.api_spring_boot.modules.auth.services.AuthenticationService;
import com.github.yangricardo.api_spring_boot.modules.auth.services.TokenIssuerService;
import com.github.yangricardo.api_spring_boot.modules.users.model.User;
import com.github.yangricardo.api_spring_boot.shared.modules.controllers.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController extends BaseController {

  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private AuthenticationService authenticationService;

  @Autowired
  private TokenIssuerService tokenIssuerService;

  @PostMapping("/login")
  public ResponseEntity<?> login(@RequestBody @Validated LoginDTO loginDTO) {
    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
        loginDTO.getUsername(), loginDTO.getPassword());

    Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);

    String jwt = tokenIssuerService.generateToken(authentication);
    TokenDTO token = TokenDTO.builder().token(jwt).type("Bearer").build();
    return ResponseEntity.ok(token);
  }

  @PostMapping("/sign-up")
  public ResponseEntity<?> signUp(@RequestBody CreateUserDTO createUserDTO) {
    try {
      User user = authenticationService.createUser(createUserDTO);
      return ResponseEntity.ok(user);
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
  }
}
