package br.pucrio.les.esg_token_backend.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

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

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    Value create(@Valid @RequestBody Value value) {
        return this.valueService.create(value);
    }

    @GetMapping("/{id}")
    @ResponseStatus(code = HttpStatus.FOUND)
    Optional<Value> findById(@PathVariable("id") Long id) {
        return this.valueService.findById(id);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    Optional<Value> updateById(@PathVariable("id") Long id, @Valid @RequestBody Value value) {
        return this.valueService.update(id, value);
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    ResponseEntity<?> deleteById(@PathVariable("id") Long id) {
        try {
            this.valueService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
