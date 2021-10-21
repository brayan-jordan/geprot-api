package net.weg.gestor.api.model.usuario;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioLoginInputDTO {

    private String email;

    private String senha;
}
