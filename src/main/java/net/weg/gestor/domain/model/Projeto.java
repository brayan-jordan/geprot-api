package net.weg.gestor.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.FieldDefaults;
import net.weg.gestor.domain.service.ValidationGroups;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Email;
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
    long idProjeto;

    @NotBlank
    @Size(min = 5)
    String nomeProjeto;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    Date dataInicio;

    Date dataFinalizacao;

    @NotNull
    int horasPrevistas;

    int horasTrabalhadas;

    @NotNull
    double valorProjeto;

    double valorUtilizado;

    @NotNull
    double valorRestante;

    @NotNull
    @ManyToOne
    @ConvertGroup(from = Default.class, to = ValidationGroups.GestorId.class)
    @Valid
    Gestor gestor;

    @Enumerated(EnumType.STRING)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private StatusProjeto statusProjeto;

}
