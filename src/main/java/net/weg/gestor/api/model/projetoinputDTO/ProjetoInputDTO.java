package net.weg.gestor.api.model.projetoinputDTO;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
public class ProjetoInputDTO {

    @NotNull
    private String nome;

    private String descricao;

    @Size(min = 1)
    private List<AlocarCCPagantesInputDTO> ccpagantes;

    @Size(min = 1)
    private List<AlocarConsultoresInputDTO> consultores;

}
