package net.weg.gestor.api.model.input;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ConsultorInputDTO {

    private UsuarioInputDTO usuario;

    private FornecedorInputDTO fornecedor;

    private double precoHora;

    private List<SkillInput> skills;

}
