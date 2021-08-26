package net.weg.gestor.api.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CCPagantesDTO {

    private int codigo;

    private int taxa;

    private CentroDeCustoDTO centrodecusto;

    private ProjetoDTO projeto;

}
