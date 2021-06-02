package net.weg.gestor.domain.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Gestor {

    @Id
    @NotNull
    Long idGestor;

    @NotBlank
    @Size(max = 100)
    String senha;

    @NotNull
    int idSecao;

    @NotBlank
    @Size(min = 5)
    String nomeSecao;

    @NotBlank
    @Size(max = 100, min = 3)
    String nomeGestor;

    @Email
    @Size(min = 10)
    String email;




}
