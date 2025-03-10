package com.parcial.crud.repositories;

import com.parcial.crud.entitys.persona_entity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface persona_repo extends JpaRepository<persona_entity, UUID> {
}
