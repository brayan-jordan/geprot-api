package net.weg.gestor.api.model;

import lombok.Getter;
import lombok.Setter;
import net.weg.gestor.domain.entities.StatusApontamento;

@Getter
@Setter
public class ConsultorAlocadoDTO {

    private String nome;

    private Long id;

    private FornecedorDTO fornecedor;

    private StatusApontamento statusApontamento;

    private int horasTotais;

}
