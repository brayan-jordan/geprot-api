package net.weg.gestor.domain.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Gestor {


    @Id
    long id;

    @NotBlank
    @Size(max = 100, min = 20)
    String nome;

    @Email
    String email;

    @NotBlank
    @Size(max = 100)
    String senha;

}
