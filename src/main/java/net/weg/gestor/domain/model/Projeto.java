package net.weg.gestor.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.FieldDefaults;
import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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
    long gestorid;

    @Enumerated(EnumType.STRING)
    private StatusProjeto statusprojeto;

}