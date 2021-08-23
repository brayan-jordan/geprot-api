package net.weg.gestor.api.model.centrodecustoinputDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CCPagantesInput {

    private CCPagante_ProjetoInput projeto;

    private CCPagante_CCInput centrodecusto;

    private int taxa;

}
