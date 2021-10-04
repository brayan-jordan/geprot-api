package net.weg.gestor.api.model;

import lombok.Getter;
import lombok.Setter;
import net.weg.gestor.domain.entities.Secao;
import net.weg.gestor.domain.entities.Usuario;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class GestorDTO {

    private Long id;

    private SecaoDTO secao;

    private UsuarioDTO usuario;


}
