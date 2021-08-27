package net.weg.gestor.api.model.projetoinputDTO;

import lombok.Getter;
import lombok.Setter;
import net.weg.gestor.api.model.centrodecustoinputDTO.CCPagantesInputDTO;

import java.util.List;

@Getter
@Setter
public class ProjetoInteiroInputDTO {

    private ProjetoInputDTO projeto;

    private List<CCPagantesInputDTO> ccpagantes;

}
