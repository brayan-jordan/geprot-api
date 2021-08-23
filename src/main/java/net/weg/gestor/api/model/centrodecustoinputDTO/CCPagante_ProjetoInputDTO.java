package net.weg.gestor.api.model.centrodecustoinputDTO;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CCPagante_ProjetoInputDTO {

    @NotNull
    private Long id;

}
