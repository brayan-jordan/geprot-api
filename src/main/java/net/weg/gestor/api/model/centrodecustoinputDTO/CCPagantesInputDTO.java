package net.weg.gestor.api.model.centrodecustoinputDTO;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CCPagantesInputDTO {

    @NotNull
    private int taxa;

    private CCPagante_ProjetoInputDTO projeto;

    @NotNull
    private CCPagante_CCInputDTO centrodecusto;

}
