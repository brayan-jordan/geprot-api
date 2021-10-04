package net.weg.gestor.api.modelantiga;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HorasApontadasTotalDTO {

    private Long consultor_id;

    private String nome;

    private String status;

    private int horasTotais;

    private int quantidade_horas;

}
