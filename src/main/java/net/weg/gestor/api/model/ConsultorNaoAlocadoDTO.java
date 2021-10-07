package net.weg.gestor.api.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import net.weg.gestor.api.model.FornecedorDTO;

@Getter
@Setter
public class ConsultorNaoAlocadoDTO {

    private Long id;

    private String nome;

    private FornecedorDTO fornecedor;

    private int quantidade_projetos_alocado;

}
