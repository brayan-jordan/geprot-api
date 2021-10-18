package net.weg.gestor.api.model.consultorhoras;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ConsultorAndHorasDTO {

    private String nome;

    private List<HoraApontadaDTO> horas;

    private int horasTotais;

    private double totalGasto;

    private double precoHora;

}
