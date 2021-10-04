package net.weg.gestor.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cc_pagantes")
public class CCPagantes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @JoinColumn(name = "projetos_id")
    @ManyToOne
    private Projeto projeto;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "secoes_id")
    private Secao secao;

    @NotNull
    private double taxa;

}
