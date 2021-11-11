package net.weg.gestor.api.model.input;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ConsultorInputDTO {

    private long id;

    private UsuarioInputDTO usuario;

    private FornecedorInputDTO fornecedor;

    private double precoHora;

    private List<SkillInput> skills;

}
