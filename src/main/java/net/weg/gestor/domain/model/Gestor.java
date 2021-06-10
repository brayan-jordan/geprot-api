package net.weg.gestor.domain.model;

import lombok.*;
import net.weg.gestor.domain.service.ValidationGroups;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.validation.groups.ConvertGroup;
import javax.validation.groups.Default;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "gestores")
public class Gestor {

    @Id
    long idgestor;

    @NotBlank
    @Size(max = 100)
    String senha;

    @Valid
    @NotNull
    private long idsecao;

    @NotBlank
    @Size(max = 100, min = 3)
    String nomegestor;

    @Email
    @NotBlank
    @Size(min = 10)
    String email;
}
