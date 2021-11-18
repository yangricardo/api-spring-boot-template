package br.pucrio.les.esg_token_backend.configurations.base.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(maxAge = 3600)
public abstract class BaseController {
    public static ResponseEntity<?> badRequestValidationResponseEntity(Errors errors) {
        return ResponseEntity.badRequest()
                .body(errors.getAllErrors()/*
                                            * .stream().map(msg ->
                                            * msg.getDefaultMessage()).collect(Collectors.joining(","))
                                            */);
    }
}
