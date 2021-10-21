package net.weg.gestor.api.model.consultor;

import lombok.Getter;
import lombok.Setter;
import net.weg.gestor.api.model.FornecedorDTO;
import net.weg.gestor.api.model.usuario.UsuarioDTO;

@Getter
@Setter
public class ConsultorDTO {

    private long id;

    private UsuarioDTO usuario;

    private FornecedorDTO fornecedor;

    private double precoHora;



}
