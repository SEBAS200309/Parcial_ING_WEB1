package com.parcial.crud.controllers;

import com.parcial.crud.entitys.persona_entity;
import com.parcial.crud.services.persona_service;
import org.springframework.web.bind.annotation.*;

        import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/nombres")
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
    public persona_entity create(@RequestBody persona_entity entity) {
        return service.save(entity);
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