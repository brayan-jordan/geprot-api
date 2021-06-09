package net.weg.gestor.domain.model;

import lombok.*;
import lombok.experimental.FieldDefaults;
import net.weg.gestor.domain.service.ValidationGroups;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
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
    long idGestor;

    @NotBlank
    @Size(max = 100)
    String senha;

    @Valid
    @ConvertGroup(from = Default.class, to = ValidationGroups.SecaoId.class)
    @NotNull
    @ManyToOne
    private Secao secao;

    @NotBlank
    @Size(max = 100, min = 3)
    String nomeGestor;

    @Email
    @NotBlank
    @Size(min = 10)
    String email;
}
