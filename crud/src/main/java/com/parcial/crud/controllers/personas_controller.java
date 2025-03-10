package com.parcial.crud.controllers;

import com.parcial.crud.entitys.persona_entity;
import com.parcial.crud.services.persona_service;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @PutMapping("/{id}")
    public persona_entity update(@PathVariable UUID id, @RequestBody persona_entity entity) {
        return service.update(id,entity);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        service.delete(id);
    }

    // Manejo de errores de validación (400 Bad Request)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        String errorMessage = ex.getBindingResult()
                .getAllErrors()
                .stream()
                .map(error -> error.getDefaultMessage())
                .reduce((message1, message2) -> message1 + ", " + message2)
                .orElse("Error de validación");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
    }

    // Manejo de errores generales (500 Internal Server Error)
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<String> handleGlobalException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error interno del servidor: " + ex.getMessage());
    }

    // Manejo de errores de ejecución (500 Internal Server Error)
    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<String> handleRuntimeException(RuntimeException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error en la ejecución: " + ex.getMessage());
    }
}
