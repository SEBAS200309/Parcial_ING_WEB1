package com.parcial.crud.entitys;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;
import java.util.UUID;

@Entity
@Table(name = "nombres")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class persona_entity {
    @Id
    private UUID id;
    private String nombre;
    private String segundonombre;
    private String primerapellido;
    private String segundoapellido;

    @PrePersist
    public void generateUUID(){
        if(id == null){
            id = UUID.randomUUID();
        }
    }
}

