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
import java.util.Date;

@Entity
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@Table(name = "projetos")
public class Projeto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    long idprojeto;

    @NotBlank
    @Size(min = 5)
    String nomeprojeto;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    Date datainicio;

    Date datafinalizacao;

    @NotNull
    int horasprevistas;

    int horastrabalhadas;

    @NotNull
    double valorprojeto;

    double valorutilizado;

    @NotNull
    double valorrestante;

    @NotNull
    @ManyToOne
    @ConvertGroup(from = Default.class, to = ValidationGroups.Gestorid.class)
    @Valid
    Gestor gestor;

    @Enumerated(EnumType.STRING)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private StatusProjeto statusprojeto;

}
