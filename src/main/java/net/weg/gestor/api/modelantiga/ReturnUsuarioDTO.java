package net.weg.gestor.api.modelantiga;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReturnUsuarioDTO {

    private String nome;
    private String email;
    private Long id;
    private SecaoDoUsuarioDTO secao;

}
