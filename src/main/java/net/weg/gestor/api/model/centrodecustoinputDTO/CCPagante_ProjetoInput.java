package net.weg.gestor.api.model.centrodecustoinputDTO;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CCPagante_ProjetoInput {

    @NotNull
    private Long idprojeto;

}
