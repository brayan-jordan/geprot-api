package net.weg.gestor.api.model.consultor;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import net.weg.gestor.api.model.FornecedorDTO;
import net.weg.gestor.api.model.input.SkillDTO;

import java.util.List;

@Getter
@Setter
public class ConsultorNaoAlocadoDTO {

    private Long id;

    private String nome;

    private FornecedorDTO fornecedor;

    private List<SkillDTO> skills;

    private int quantidade_projetos_alocado;

}
