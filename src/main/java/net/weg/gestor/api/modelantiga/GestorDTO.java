package net.weg.gestor.api.modelantiga;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GestorDTO {

    private Long id;
    private SecoesDTO secao;
    private UsuarioDTO usuario;

}
