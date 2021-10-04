package net.weg.gestor.api.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioLoginInputDTO {

    private String email;

    private String senha;
}
