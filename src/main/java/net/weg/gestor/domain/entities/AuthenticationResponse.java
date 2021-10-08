package net.weg.gestor.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import net.weg.gestor.api.model.GestorDTO;

@AllArgsConstructor
@Getter
public class AuthenticationResponse {

    private String jwt;

    private GestorDTO gestorDTO;

}
