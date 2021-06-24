package net.weg.gestor.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CCPagantesModel {

    private int codigo;

    private ProjetoModel projeto;

    private CentroDeCustoModel centroDeCusto;

    private int taxa;

}
