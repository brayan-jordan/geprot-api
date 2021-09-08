package net.weg.gestor.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@Table(name = "secoes")
public class Secao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @NotBlank
    @Size(min = 5, max = 100)
    String nome;

    @NotBlank
    double verba;

    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "cc_pagantes", joinColumns =
    @JoinColumn(name = "secoes_id", referencedColumnName = "id"), inverseJoinColumns =
    @JoinColumn(name = "projetos_id", referencedColumnName = "id"))
    List<Projeto> projetos;

}
