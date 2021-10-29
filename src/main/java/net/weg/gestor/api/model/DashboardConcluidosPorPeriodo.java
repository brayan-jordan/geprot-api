package net.weg.gestor.api.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class DashboardConcluidosPorPeriodo {


    private String periodo;

    private int quantidadeConcluidos;
}
