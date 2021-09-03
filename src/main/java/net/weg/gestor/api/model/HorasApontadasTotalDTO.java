package net.weg.gestor.api.model;

import lombok.Getter;
import lombok.Setter;
import net.weg.gestor.domain.model.StatusApontamento;

@Getter
@Setter
public class HorasApontadasTotalDTO {

    private Long consultor_id;

    private String nome;

    private StatusApontamento status;

    private int horasTotais;

}
