package net.weg.gestor.api.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GestorModel {

    private long idgestor;
    private String nomegestor;
    private UsuarioDTO usuarioDTO;
    private SecaoModel secao;
    private UsuarioDTO usuario;
}
