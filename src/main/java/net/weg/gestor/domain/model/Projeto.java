package net.weg.gestor.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.FieldDefaults;
import net.weg.gestor.domain.service.ValidationGroups;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.validation.groups.ConvertGroup;
import javax.validation.groups.Default;
import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@Table(name = "projetos")
@NoArgsConstructor
public class Projeto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull(groups = ValidationGroups.Idprojeto.class)
    Long idprojeto;

    @NotBlank
    @Size(min = 5)
    String nomeprojeto;

    LocalDateTime datainicio;

    LocalDateTime datafinalizacao;

    @NotNull
    int horasprevistas;

    int horastrabalhadas;

    @NotNull
    double valorprojeto;

    double valorutilizado;

    double valorrestante;

    @NotNull
    @Valid
    @ManyToOne(cascade = CascadeType.ALL)
    @ConvertGroup(from = Default.class, to = ValidationGroups.Gestorid.class)
    private Gestor gestor;

    @Enumerated(EnumType.STRING)
    private StatusProjeto statusprojeto;

}
