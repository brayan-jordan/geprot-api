package net.weg.gestor.api.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class DashboardConcluidosPorMes {

    private String mes;

    private int quantidadeConcluidos;

}
