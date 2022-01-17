package com.github.yangricardo.api_spring_boot.modules.value.controllers;

import com.github.yangricardo.api_spring_boot.modules.value.model.Value;
import com.github.yangricardo.api_spring_boot.modules.value.services.IValueService;
import com.github.yangricardo.api_spring_boot.shared.modules.controllers.BaseController;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/values")
public class ValueController extends BaseController {

  @Autowired
  private IValueService valueService;

  public ValueController(IValueService valueService) {
    this.valueService = valueService;
  }

  @GetMapping
  @ResponseStatus(code = HttpStatus.OK)
  @ApiOperation(value = "Find all values")
  List<Value> index() {
    return this.valueService.index();
  }

  @PostMapping
  @ApiOperation(value = "Create value")
  @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
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
    return this.valueService.findById(id)
      .map(value -> ResponseEntity.status(HttpStatus.FOUND).body(value))
      .orElseGet(() -> ResponseEntity.notFound().build());
  }

  @PatchMapping("/{id}")
  @ApiOperation(value = "Update value by id")
  ResponseEntity<?> updateById(
    @PathVariable("id") Long id,
    @Valid @RequestBody Value value,
    Errors errors
  ) {
    if (!errors.hasErrors()) {
      return this.valueService.update(id, value)
        .map(valueUpdated -> ResponseEntity.ok(valueUpdated))
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
