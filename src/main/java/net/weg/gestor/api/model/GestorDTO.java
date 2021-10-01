package net.weg.gestor.api.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GestorDTO {

    private Long id;
    private SecoesDTO secao;
    private UsuarioDTO usuario;

}
