package net.weg.gestor.api.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class DashboardConcluidos {

    private LocalDate data;

    private int quantidadeConcluidos;

}
