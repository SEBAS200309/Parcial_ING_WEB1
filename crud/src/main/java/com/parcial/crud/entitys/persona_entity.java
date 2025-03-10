package com.parcial.crud.entitys;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
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

    @NotBlank(message = "El nombre no puede estar vacío")
    @Pattern(regexp = "^.{3,}$", message = "El nombre debe tener al menos 3 caracteres")
    private String nombre;

    private String segundonombre;

    @NotBlank(message = "El primer apellido no puede estar vacío")
    @Pattern(regexp = "^.{3,}$", message = "El primer apellido debe tener al menos 3 caracteres")
    private String primerapellido;

    @NotBlank(message = "El segundo apellido no puede estar vacío")
    @Pattern(regexp = "^.{3,}$", message = "El segundo apellido debe tener al menos 3 caracteres")
    private String segundoapellido;

    @PrePersist
    public void generateUUID(){
        if(id == null){
            id = UUID.randomUUID();
        }
    }
}


