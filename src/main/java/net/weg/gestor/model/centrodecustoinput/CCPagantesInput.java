package net.weg.gestor.model.centrodecustoinput;

import lombok.Getter;
import lombok.Setter;
import net.weg.gestor.domain.model.Projeto;
import net.weg.gestor.model.projetoinput.ProjetoInput;

@Getter
@Setter
public class CCPagantesInput {

    private CCPagante_ProjetoInput projetoInput;

    private CCPagante_CCInput ccInput;

    private int taxa;

}
