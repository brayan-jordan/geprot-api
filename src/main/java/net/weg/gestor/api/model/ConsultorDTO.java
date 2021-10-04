package net.weg.gestor.api.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConsultorDTO {

    private long id;

    private UsuarioDTO usuario;

    private FornecedorDTO fornecedor;

    private double preco_hora;

}
