package net.weg.gestor.api.model.input;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioInputDTO {

    private String nome;

    private String email;

    private String senha;
}
