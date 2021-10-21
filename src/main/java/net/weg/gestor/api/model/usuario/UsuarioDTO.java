package net.weg.gestor.api.model.usuario;

import lombok.Getter;
import lombok.Setter;
import net.weg.gestor.domain.entities.StatusUsuario;

import java.time.LocalDate;

@Getter
@Setter
public class UsuarioDTO {

    private Long id;

    private String nome;

    private String email;

    private LocalDate dataCadastro;

    private StatusUsuario status;

}
