package com.parcial.crud.services;

import com.parcial.crud.entitys.persona_entity;
import com.parcial.crud.repositories.persona_repo;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class persona_service {
    private final persona_repo repository;

    public persona_service(persona_repo repository) {
        this.repository = repository;
        generarRegistros();
    }

    public List<persona_entity> generarRegistros() {
        List<persona_entity> registros = IntStream.rangeClosed(1, 1000)
                .mapToObj(i -> new persona_entity(
                        UUID.randomUUID(),  // Genera un UUID único para cada registro
                        "Nombre" + i,
                        "SegundoNombre" + i,
                        "PrimerApellido" + i,
                        "SegundoApellido" + i
                ))
                .collect(Collectors.toList());

        return repository.saveAll(registros);
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

    public persona_entity update(UUID id, persona_entity entity) {
        entity.setId(id);
        return repository.save(entity);
    }
}