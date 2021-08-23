package net.weg.gestor.api.model;

import lombok.Getter;
import lombok.Setter;
import net.weg.gestor.domain.model.Secao;

@Getter
@Setter
public class UsuarioDTO {

    private long id;
    private String nome;
    private Secao secao;
    private String email;
    private String senha;

}
