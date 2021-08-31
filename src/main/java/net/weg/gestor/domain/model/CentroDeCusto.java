package net.weg.gestor.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "centros_de_custo")
public class CentroDeCusto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private String nome;

    @ManyToMany
    @JoinTable(name = "cc_pagantes", joinColumns =
        @JoinColumn(name = "centros_de_custo_id", referencedColumnName = "id"), inverseJoinColumns =
        @JoinColumn(name = "projetos_id", referencedColumnName = "id"))
    List<Projeto> projetos;

}
