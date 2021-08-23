package net.weg.gestor.api.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CCPagantesDTO {

    private int codigo;

    private CentroDeCustoDTO centrodecusto;

    private int taxa;

    private ProjetoDTO projeto;

}
