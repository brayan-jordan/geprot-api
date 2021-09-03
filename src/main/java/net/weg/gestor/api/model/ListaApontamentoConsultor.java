package net.weg.gestor.api.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ListaApontamentoConsultor {

    private List<ColunaHoraApontadaDTO> todosApontamentos;

    private int totalHoras;

    private double valorGasto;

}
