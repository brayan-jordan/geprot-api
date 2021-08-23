package net.weg.gestor.api.model.centrodecustoinputDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CCPagantesInputDTO {

    private CCPagante_ProjetoInputDTO projeto;

    private CCPagante_CCInputDTO centrodecusto;

    private int taxa;

}
