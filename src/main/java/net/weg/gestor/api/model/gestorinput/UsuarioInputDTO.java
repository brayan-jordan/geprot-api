package net.weg.gestor.api.model.gestorinput;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class UsuarioInputDTO {

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String senha;
}
