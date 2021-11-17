package br.pucrio.les.esg_token_backend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import br.pucrio.les.esg_token_backend.models.Value;
import br.pucrio.les.esg_token_backend.services.value.IValueService;

@RestController
@RequestMapping("/values")
public class ValueController {
    @Autowired
    private IValueService valueService;

    public ValueController(IValueService valueService) {
        this.valueService = valueService;
    }

    @GetMapping
    @ResponseStatus(code = HttpStatus.FOUND)
    List<Value> index() {
        return this.valueService.index();
    }
}
