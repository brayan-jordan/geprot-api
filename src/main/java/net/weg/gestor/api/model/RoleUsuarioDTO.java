package net.weg.gestor.api.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoleUsuarioDTO {

    private Long id;

    private String role_nome;

    private Long usuarios_id;

}
