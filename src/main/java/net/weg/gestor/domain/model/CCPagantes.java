package net.weg.gestor.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

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

    @JoinColumn(name = "centrodecusto_id")
    @ManyToOne(cascade = CascadeType.ALL)
    private CentroDeCusto centrodecusto;

    @JoinColumn(name = "projeto_id")
    @ManyToOne(cascade = CascadeType.ALL)
    private Projeto projeto;

    private int taxa;

}
