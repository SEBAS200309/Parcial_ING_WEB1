package com.parcial.crud.controllers;

import com.parcial.crud.entitys.persona_entity;
import com.parcial.crud.services.persona_service;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

        import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/v2.0/api/nombres")
public class personas_controller {
    private final persona_service service;

    public personas_controller(persona_service service) {
        this.service = service;
    }


    @GetMapping
    public List<persona_entity> getAll() {
        return service.getAll();
    }

    @PostMapping
    public persona_entity create(@Valid @RequestBody persona_entity entity) {
        return service.save(entity);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleValidationExceptions(MethodArgumentNotValidException ex) {
        return ex.getBindingResult()
                .getAllErrors()
                .stream()
                .map(error -> error.getDefaultMessage())
                .reduce((message1, message2) -> message1 + ", " + message2)
                .orElse("Error de validación");
    }

    @PutMapping("/{id}")
    public persona_entity update(@PathVariable UUID id, @RequestBody persona_entity entity) {
        entity.setId(id);
        return service.save(entity);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        service.delete(id);
    }
}