package net.weg.gestor.api.model.centrodecustoinput;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CCPagantesInput {

    private CCPagante_ProjetoInput projeto;

    private CCPagante_CCInput centrodecusto;

    private int taxa;

}
