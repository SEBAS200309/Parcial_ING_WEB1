package com.parcial.crud.services;

import com.parcial.crud.entitys.persona_entity;
import com.parcial.crud.repositories.persona_repo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class persona_service {
    private final persona_repo repository;

    public persona_service(persona_repo repository) {
        this.repository = repository;

    }

    public List<persona_entity> getAll() {
        return repository.findAll();
    }

    public persona_entity save(persona_entity entity) {
        return repository.save(entity);
    }

    public void delete(UUID id) {
        repository.deleteById(id);
    }

}