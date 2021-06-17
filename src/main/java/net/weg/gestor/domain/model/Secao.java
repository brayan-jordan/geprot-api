package net.weg.gestor.domain.model;

import lombok.*;
import lombok.experimental.FieldDefaults;
import net.weg.gestor.domain.service.ValidationGroups;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@Table(name = "secoes")
public class Secao {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull(groups = ValidationGroups.Secaoid.class)
    long idsecao;

    @NotBlank
    @Size(min = 5, max = 100)
    String nomesecao;

}
