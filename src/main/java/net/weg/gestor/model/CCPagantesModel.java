package net.weg.gestor.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CCPagantesModel {

    private int codigo;

    private CentroDeCustoModel centrodecusto;

    private int taxa;

    private ProjetoModel projeto;

}
