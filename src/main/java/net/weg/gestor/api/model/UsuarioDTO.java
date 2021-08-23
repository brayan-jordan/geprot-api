package net.weg.gestor.api.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioDTO {

    private long id;
    private String nome;
    private SecaoDTO secao;
    private String email;
    private String senha;

}
