package net.weg.gestor.api.model.consultor;

import lombok.Getter;
import lombok.Setter;
import net.weg.gestor.api.model.FornecedorDTO;
import net.weg.gestor.domain.entities.StatusApontamento;

@Getter
@Setter
public class ConsultorAlocadoDTO {

    private String nome;

    private Long id;

    private FornecedorDTO fornecedor;

    private StatusApontamento statusApontamento;

    private int limiteHoras;

    private int horasApontadas;

    public ConsultorAlocadoDTO(String nome, Long id, FornecedorDTO fornecedor, int limiteHoras, int horasApontadas) {
        this.nome = nome;
        this.id = id;
        this.fornecedor = fornecedor;
        this.limiteHoras = limiteHoras;
        this.horasApontadas = horasApontadas;
    }
}
