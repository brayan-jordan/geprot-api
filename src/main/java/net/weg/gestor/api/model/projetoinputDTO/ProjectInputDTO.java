package net.weg.gestor.api.model.projetoinputDTO;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
public class ProjectInputDTO {

    @NotNull
    private String nome;

    private int horasPrevistas;

    @NotNull
    private double valor;

    @Size(min = 1)
    private List<ProjectInputCCPagDTO> ccpagantes;

    @Size(min = 1)
    private List<ProjectInputConsAlocDTO> consultores;

}
