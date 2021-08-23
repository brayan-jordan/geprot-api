package net.weg.gestor.api.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoleUsuarioDTO {

    private Long id;

    private String nome_role;

    private Long id_usuarios;

}
