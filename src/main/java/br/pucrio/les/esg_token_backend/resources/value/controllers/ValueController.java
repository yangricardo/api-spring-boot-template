package br.pucrio.les.esg_token_backend.resources.value.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;

import br.pucrio.les.esg_token_backend.configurations.base.controllers.BaseController;
import br.pucrio.les.esg_token_backend.resources.value.model.Value;
import br.pucrio.les.esg_token_backend.resources.value.services.IValueService;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/values")
public class ValueController extends BaseController {
    @Autowired
    private IValueService valueService;

    public ValueController(IValueService valueService) {
        this.valueService = valueService;
    }

    @GetMapping
    @ResponseStatus(code = HttpStatus.FOUND)
    @ApiOperation(value = "Find all values")
    List<Value> index() {
        return this.valueService.index();
    }

    @PostMapping
    @ApiOperation(value = "Create value")
    ResponseEntity<?> create(@Valid @RequestBody Value value, Errors errors) {
        if (!errors.hasErrors()) {
            Value valueCreated = this.valueService.create(value);
            return ResponseEntity.status(HttpStatus.CREATED).body(valueCreated);
        } else {
            // System.err.println(errors);
            return badRequestValidationResponseEntity(errors);
        }
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Find value by id")
    ResponseEntity<?> findById(@PathVariable("id") Long id) {
        return this.valueService.findById(id).map(value -> ResponseEntity.status(HttpStatus.FOUND).body(value))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PatchMapping("/{id}")
    @ApiOperation(value = "Update value by id")
    ResponseEntity<?> updateById(@PathVariable("id") Long id, @Valid @RequestBody Value value, Errors errors) {
        if (!errors.hasErrors()) {
            return this.valueService.update(id, value).map(valueUpdated -> ResponseEntity.ok(valueUpdated))
                    .orElseGet(() -> ResponseEntity.notFound().build());

        } else {
            // System.err.println(errors);
            return badRequestValidationResponseEntity(errors);
        }
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete value by id")
    ResponseEntity<?> deleteById(@PathVariable("id") Long id) {
        try {
            this.valueService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
