package net.weg.gestor.api.model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@NotNull
public class ProjetoInteiroDTO {


    private ProjetoDTO projeto;

    private List<CCPagantesDTO> ccpagantes;

}
