package net.weg.gestor.api.model;

import lombok.Getter;
import lombok.Setter;
import net.weg.gestor.api.model.usuario.UsuarioDTO;

@Getter
@Setter
public class GestorDTO {

    private Long id;

    private SecaoDTO secao;

    private UsuarioDTO usuario;


}
