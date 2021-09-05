package net.weg.gestor.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import net.weg.gestor.api.model.ReturnUsuarioDTO;

@AllArgsConstructor
@Getter
public class AuthenticationResponse {

    private String jwt;
    private ReturnUsuarioDTO usuario;

}
