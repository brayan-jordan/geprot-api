package net.weg.gestor.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

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
    Long id;

    @NotBlank
    @Size(min = 5)
    String nome;

    LocalDate dataInicio;

    LocalDate dataFinalizacao;

    LocalDate dataCadastro;

    @NotNull
    int horasPrevistas;

    int horasTrabalhadas;

    @NotNull
    double valor;

    double valorUtilizado;

    @Enumerated(EnumType.STRING)
    private StatusProjeto status;

    private String descricao;

    @NotNull
    private String nomeSolicitante;

    @NotNull
    private String nomeResponsavel;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "consultores_alocados", joinColumns =
        @JoinColumn(name = "projetos_id", referencedColumnName = "id"), inverseJoinColumns =
        @JoinColumn(name = "consultores_id", referencedColumnName = "id"))
    List<Consultor> consultores;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "cc_pagantes", joinColumns =
        @JoinColumn(name = "projetos_id", referencedColumnName = "id"), inverseJoinColumns =
        @JoinColumn(name = "secoes_id", referencedColumnName = "id"))
    List<Secao> secaos;

}
