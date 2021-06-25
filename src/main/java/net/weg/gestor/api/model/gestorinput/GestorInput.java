package net.weg.gestor.api.model.gestorinput;

import lombok.Getter;
import lombok.Setter;
import net.weg.gestor.api.model.UsuarioDTO;

@Getter
@Setter
public class GestorInput {

    private SecaoInput secao;
    private String nomegestor;
    private long idgestor;
    private GestorInput gestorInput;
    private UsuarioInputDTO usuario;

}
