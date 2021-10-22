package net.weg.gestor.api.model.dashboard;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DashboardProjetosDTO {

    private double projetosNaoIniciados;

    private double projetosAtrasados;

    private double projetosEmAndamento;

    private double projetosConcluidos;

    private double restoProjetosNaoIniciados;

    private double restoProjetosAtrasados;

    private double restoProjetosEmAndamento;

    private double restoProjetosConcluidos;



}
