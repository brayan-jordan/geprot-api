package net.weg.gestor.api.modelantiga;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DashboardSecaoDTO {

    private double projetosConcluidos;

    private double projetosAtrasados;

    private double projetosEmAndamento;

    private double projetosNaoIniciados;

    private double verbasDisponivel;

    private double verbasAprovadas;

    private double restoProjetosAtrasados;

    private double restoProjetosEmAndamento;

    private double restoProjetosNaoIniciados;



}