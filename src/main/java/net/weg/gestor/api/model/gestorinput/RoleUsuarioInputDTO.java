package net.weg.gestor.api.model.gestorinput;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class RoleUsuarioInputDTO {

    @NotNull
    private Long cadastro;

    @NotBlank
    private String nome_role;

}
