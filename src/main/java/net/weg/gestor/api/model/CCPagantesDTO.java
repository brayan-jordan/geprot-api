package net.weg.gestor.api.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CCPagantesDTO {

    private Long id;

    private int taxa;

    private CentroDeCustoDTO centrodecusto;

}
