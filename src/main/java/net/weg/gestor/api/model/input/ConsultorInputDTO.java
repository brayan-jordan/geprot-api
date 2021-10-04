package net.weg.gestor.api.model.input;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConsultorInputDTO {

    private UsuarioInputDTO usuario;

    private FornecedorInputDTO fornecedor;

    private double preco_hora;

}
