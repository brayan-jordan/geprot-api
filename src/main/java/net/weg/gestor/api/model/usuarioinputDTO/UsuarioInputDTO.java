package net.weg.gestor.api.model.usuarioinputDTO;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class UsuarioInputDTO {

    private String nome;

    private long id;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String senha;

}
